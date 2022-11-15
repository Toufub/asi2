package com.asi2.user.controller;

import com.asi2.common.model.UserDTO;
import com.asi2.user.tools.UserMapper;
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
        Optional<User> usersDaoOptional = userRepository.findById(id);
        if(usersDaoOptional.isPresent()){
            return usersDaoOptional.get();
        } else {
            return null;
        }
    }

    public void deleteUser(final int id) {
        userRepository.deleteById(id);
    }

    public User createUser(User user) {
        User savedUser = userRepository.save(user);
        return savedUser;
    }

    public UserDTO putUser(final int id, UserDTO newUser) {
        return this.userRepository.findById(id)
                .map(user -> {
                    user.setAccount(newUser.getAccount());
                    return UserMapper.UserToDTO(this.userRepository.save(user));
                })
                .orElseGet(() -> {
                    return null;
                });
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
