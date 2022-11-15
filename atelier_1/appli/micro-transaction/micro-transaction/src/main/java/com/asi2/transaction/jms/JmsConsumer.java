package com.asi2.transaction.jms;

import com.asi2.common.model.CardDTO;
import com.asi2.common.model.TransactionDTO;
import com.asi2.common.model.UserDTO;
import com.asi2.transaction.controller.MicroTransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@Component
@Slf4j
public class JmsConsumer {

    private final String user_url = "http://localhost:8081/users/";
    private final String card_url = "http://localhost:8082/cards/";
    private MicroTransactionService microTransactionService;
    private JmsProducer jmsProducer;
    private RestTemplate restTemplate;

    public JmsConsumer(MicroTransactionService microTransactionService,
                       JmsProducer jmsProducer, RestTemplateBuilder restTemplateBuilder) {
        this.microTransactionService = microTransactionService;
        this.jmsProducer = jmsProducer;
        this.restTemplate = restTemplateBuilder.build();
    }

    @JmsListener(destination = "transaction.create")
    public void onCreateMessage(Object [] parameters) {
        try{
            log.info("Received Message: transaction.create");
            String login = (String)parameters[1];
            TransactionDTO transactionDTO = (TransactionDTO)parameters[0];
            UserDTO user = this.getUser(login);
            CardDTO card = this.getCard(transactionDTO.getCardId());
            int card_id = card.getId();
            if(card.getUserId() == user.getId()) {
                card.setPrice(transactionDTO.getPrice());
                this.modifyCard(card_id, card);
                jmsProducer.sendCreationTermineeMessage(1);
            } else {
                jmsProducer.sendCreationTermineeMessage(0);
            }
        } catch(Exception e) {
            jmsProducer.sendCreationTermineeMessage(0);
            log.error("Received Exception : "+ e);
        }
    }

        @JmsListener(destination = "transaction.buy")
    public void onPutMessage(Object [] parameters) {
        try{
            log.info("Received Message: transaction.create");
            String login = (String)parameters[2];
            int transactionId = (int)parameters[0];
            TransactionDTO transactionDTO = (TransactionDTO)parameters[1];
            UserDTO user = this.getUser(login);
            UserDTO seller = this.getUserById(transactionDTO.getUserId());
            CardDTO card = this.getCard(transactionDTO.getCardId());
            int card_id = card.getId();
            if(transactionDTO.getBuyerId() == null && transactionDTO.getTransactionDate() == null) {
                if (card.getUserId() != user.getId() && card.getPrice() > 0) {
                    if (this.modifyUser(user.getId(), user, card.getPrice())) {
                        this.modifyUser(seller.getId(), seller, -card.getPrice()); // - sur le prix pour incrémenter
                        transactionDTO.setBuyerId(user.getId());
                        transactionDTO.setTransactionDate(new Date());
                        this.microTransactionService.modifyTransaction(transactionId, transactionDTO);
                        card.setUserId(user.getId());
                        card.setPrice(0.0);
                        this.modifyCard(card_id, card);
                        jmsProducer.sendVenteTermineeMessage(1);
                    }
                }
            }
            jmsProducer.sendVenteTermineeMessage(0);
        } catch(Exception e) {
            jmsProducer.sendCreationTermineeMessage(0);
            log.error("Received Exception : "+ e);
        }
    }

    public UserDTO getUser(String login) {
        return this.restTemplate.getForObject(this.user_url + "login/" + login, UserDTO.class);
    }

    public UserDTO getUserById(int id) {
        return this.restTemplate.getForObject(this.user_url + Integer.toString(id), UserDTO.class);
    }

    public CardDTO getCard(int id) {
        return this.restTemplate.getForObject(this.card_url + Integer.toString(id), CardDTO.class);
    }

    public void modifyCard(int id, CardDTO cardDTO) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String resourceUrl = this.card_url + '/' + Integer.toString(id);
        HttpEntity<CardDTO> requestUpdate = new HttpEntity<>(cardDTO, headers);
        this.restTemplate.exchange(resourceUrl, HttpMethod.PUT, requestUpdate, Void.class);
    }

    public boolean modifyUser(int id, UserDTO userDTO, double price) {
        if(userDTO.getAccount() > price) {
            userDTO.setAccount(userDTO.getAccount()-price);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            String resourceUrl = this.user_url + '/' + Integer.toString(id);
            HttpEntity<UserDTO> requestUpdate = new HttpEntity<>(userDTO, headers);
            this.restTemplate.exchange(resourceUrl, HttpMethod.PUT, requestUpdate, Void.class);
            return true;
        } else {
            return false;
        }
    }

}