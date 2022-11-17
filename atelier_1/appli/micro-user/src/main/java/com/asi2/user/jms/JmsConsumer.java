package com.asi2.user.jms;

import com.asi2.user.controller.UserService;
import com.asi2.common.model.UserDTO;
import com.asi2.user.tools.UserMapper;
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

    @JmsListener(destination = "user.create")
    public void onCreateMessage(UserDTO user) {
        try{
            log.info("Received Message: "+ user.getLogin());
            userService.createUser(UserMapper.DTOtoUser(user));
            // TODO : Notif
        } catch(Exception e) {
            log.error("Received Exception : "+ e);
        }
    }

}