package com.asi2.transaction.jms;

import com.asi2.common.model.TransactionDTO;
import com.asi2.common.model.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class JmsProducer {

        @Autowired
        JmsTemplate jmsTemplate;

        public void sendCreationMessage(TransactionDTO message){
            try{
                log.info("Attempting Send message to Topic: transaction.create");
                jmsTemplate.convertAndSend("transaction.create", message);
            } catch(Exception e){
                log.error("Recieved Exception during send Message: ", e);
            }
        }

    public void sendPutMessage(Object [] parameters){
        try{
            log.info("Attempting Send message to Topic: transaction.put");
            jmsTemplate.convertAndSend("transaction.put", parameters);
        } catch(Exception e){
            log.error("Recieved Exception during send Message: ", e);
        }
    }

}
