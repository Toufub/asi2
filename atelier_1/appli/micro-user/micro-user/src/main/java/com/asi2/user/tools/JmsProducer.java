package com.asi2.user.tools;

import com.asi2.common.model.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class JmsProducer {

        @Autowired
        JmsTemplate jmsTemplate;

        @Value("${active-mq.topic}")
        private String topic;

        public void sendMessage(UserDTO message){
            try{
                log.info("Attempting Send message to Topic: "+ topic);
                jmsTemplate.convertAndSend(topic, message);
            } catch(Exception e){
                log.error("Recieved Exception during send Message: ", e);
            }
        }

}
