package com.asi2.card.jms;

import com.asi2.card.controller.CardService;
import com.asi2.common.model.CardDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class JmsConsumer {

    private CardService cardService;

    @Autowired
    public JmsConsumer(CardService cardService) {
        this.cardService = cardService;
    }

    @JmsListener(destination = "card.create")
    public void onCreateMessage(CardDTO cardDTO) {
        try{
            log.info("Received Message: "+ cardDTO.getId());
            cardService.addCard(cardDTO);
            // TODO : Notif
        } catch(Exception e) {
            log.error("Received Exception : "+ e);
        }
    }

    @JmsListener(destination = "card.put")
    public void onPutMessage(Object [] message) {
        try{
            log.info("Received Message: card-put "+ message[0]);
            cardService.modifyCard(Integer.parseInt(message[0].toString()), (CardDTO)message[1]);
            // TODO : Notif
        } catch(Exception e) {
            log.error("Received Exception : "+ e);
        }
    }
}