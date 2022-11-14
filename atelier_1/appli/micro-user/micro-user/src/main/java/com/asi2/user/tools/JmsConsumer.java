package com.asi2.user.tools;

import com.asi2.user.controller.UserService;
import com.asi2.user.model.User;
import com.asi2.user.model.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class JmsConsumer {

    private UserService userService;

    @Autowired
    public JmsConsumer(UserService userService) {
        this.userService = userService;
    }

    @JmsListener(destination = "${active-mq.topic}")
    public void onMessage(UserDTO user) {
        try{
            log.info("Received Message: "+ user.getLogin());
            User userCreate = userService.createUser(UserMapper.DTOtoUser(user));
        } catch(Exception e) {
            log.error("Received Exception : "+ e);
        }
    }
}