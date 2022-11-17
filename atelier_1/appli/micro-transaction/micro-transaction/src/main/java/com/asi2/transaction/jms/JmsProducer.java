package com.asi2.transaction.jms;

import com.asi2.common.model.TransactionDTO;
import com.asi2.common.model.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.TransactionId;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class JmsProducer {

    @Autowired
    JmsTemplate jmsTemplate;

    public void sendCreationMessage(TransactionDTO transaction, String login){
        try{
            log.info("Attempting Send message to Topic: transaction.create");
            Object [] parameters = {transaction, login};
            jmsTemplate.convertAndSend("transaction.create", parameters);
        } catch(Exception e){
            log.error("Recieved Exception during send Message: ", e);
        }
    }

    public void sendCreationTermineeMessage(int success){
        try{
            log.info("Attempting Send message to Topic: notification.transaction.create");
            jmsTemplate.convertAndSend("notification.transaction.create", success);
        } catch(Exception e){
            log.error("Recieved Exception during send Message: ", e);
        }
    }

    public void sendVenteTermineeMessage(int success){
        try{
            log.info("Attempting Send message to Topic: notification.transaction.close");
            jmsTemplate.convertAndSend("notification.transaction.close", success);
        } catch(Exception e){
            log.error("Recieved Exception during send Message: ", e);
        }
    }

    public void sendPutMessage(int id, TransactionDTO transaction, String login){
        try{
            log.info("Attempting Send message to Topic: transaction.buy");
            Object [] parameters = {id, transaction, login};
            jmsTemplate.convertAndSend("transaction.buy", parameters);
        } catch(Exception e){
            log.error("Recieved Exception during send Message: ", e);
        }
    }

}
