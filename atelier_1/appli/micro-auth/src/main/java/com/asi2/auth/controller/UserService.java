package com.asi2.auth.controller;

import com.asi2.common.model.LoginDTO;
import com.asi2.common.model.UserDTO;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class UserService {

    private final RestTemplate restTemplate;

    private final String UserEndpoint = "http://localhost:8081/users";


    public UserService(RestTemplateBuilder restTemplateBuilder){
        this.restTemplate = restTemplateBuilder.rootUri(UserEndpoint).build();
    }

    public UserDTO checkLogin(LoginDTO loginForm){
        Optional<UserDTO> userOptional = Optional.ofNullable(this.findByUsername(loginForm.getUsername())); // TODO : req
        if(userOptional.isPresent() && userOptional.get().getPwd() != null){
            String password = loginForm.getPassword();
            if(password.equals(userOptional.get().getPwd())){
                return userOptional.get();
            }
        }
        return null;
    }

    public UserDTO findByUsername(String username){
        return this.restTemplate.getForObject(UserEndpoint + "/" + username, UserDTO.class);
    }
}
