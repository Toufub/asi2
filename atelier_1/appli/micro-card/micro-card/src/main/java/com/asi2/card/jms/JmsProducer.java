package com.asi2.card.jms;

import com.asi2.common.model.CardDTO;
import com.asi2.common.model.TransactionDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class JmsProducer {

        @Autowired
        JmsTemplate jmsTemplate;

        public void sendCreationMessage(CardDTO message){
            try{
                log.info("Attempting Send message to Topic: card.create");
                jmsTemplate.convertAndSend("card.create", message);
            } catch(Exception e){
                log.error("Recieved Exception during send Message: ", e);
            }
        }

    public void sendPutMessage(Object [] parameters){
        try{
            log.info("Attempting Send message to Topic: card.put");
            jmsTemplate.convertAndSend("card.put", parameters);
        } catch(Exception e){
            log.error("Recieved Exception during send Message: ", e);
        }
    }

}
