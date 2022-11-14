package com.asi2.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asi2.user.model.User;

import java.util.Optional;
import java.util.UUID;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Iterable<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUser(int id){
        Optional<User> usersDaoOptional = userRepository.findById(UUID.fromString(String.valueOf(id)));
        if(usersDaoOptional.isPresent()){
            return usersDaoOptional.get();
        } else {
            return null;
        }
    }

    public void deleteUser(final int id) {
        userRepository.deleteById(UUID.fromString(String.valueOf(id)));
    }

    public User createUser(User user) {
        User savedUser = userRepository.save(user);
        return savedUser;
    }

    public User putUser(final int id, double money) {
        return (userRepository.findById(UUID.fromString(String.valueOf(id)))
                .map(user -> {
                    user.setAccount(money);
                    return userRepository.save(user);
                })
                .orElseGet(() -> {
                    return null;
                }));
    }

    public User checkLogin(String username, String password){
        User user = userRepository.findByUsername(username);
        if(user != null && password != ""){
            if(password.equals(user.getPwd())){
                return user;
            }
        }
        return null;
    }

}
