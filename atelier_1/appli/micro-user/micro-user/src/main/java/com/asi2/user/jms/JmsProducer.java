package com.asi2.user.jms;

import com.asi2.common.model.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;

@Component
@Slf4j
public class JmsProducer {

        @Autowired
        JmsTemplate jmsTemplate;

        public void sendCreationMessage(UserDTO message){
            try{
                log.info("Attempting Send message to Topic: user.create");
                jmsTemplate.convertAndSend("user.create", message);
            } catch(Exception e){
                log.error("Recieved Exception during send Message: ", e);
            }
        }

    public void sendPutMessage(Object [] parameters){
        try{
            log.info("Attempting Send message to Topic: user.put");
            jmsTemplate.convertAndSend("user.put", parameters);
        } catch(Exception e){
            log.error("Recieved Exception during send Message: ", e);
        }
    }

}
