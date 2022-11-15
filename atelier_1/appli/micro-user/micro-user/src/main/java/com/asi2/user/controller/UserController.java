package com.asi2.user.controller;

import com.asi2.user.jms.JmsProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.asi2.user.model.User;
import com.asi2.common.model.UserDTO;
import com.asi2.user.tools.UserMapper;

import java.text.ParseException;
import java.util.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    JmsProducer jmsProducer;

    /**
     * Read - Get one user
     * @param id The id of the user
     * @return An User object full filled
     */
    @RequestMapping(method = RequestMethod.GET, value = "/users/{id}")
    public UserDTO getById(@PathVariable Integer id) {
        return UserMapper.UserToDTO(userService.getUser(id));
    }

    /**
     * Read - Get all users
     * @return A list of User object full filled
     */
    @GetMapping("/users")
    public Iterable<UserDTO> getAll() {
        List<User> userList = (List<User>) userService.getAllUsers();
        List<UserDTO> userDTOList = new ArrayList<UserDTO>();
        for (User user : userList) {
            userDTOList.add(UserMapper.UserToDTO(user));
        }
        return userDTOList;
    }

    /**
     * Create - Add a new user
     * @param userDTO An object user
     * @return The user object saved
     */
    @PostMapping("/users")
    public UserDTO create(@RequestBody UserDTO userDTO) throws ParseException {
        this.jmsProducer.sendCreationMessage(userDTO);
        return userDTO;
    }

    /**
     * Put - Money
     * @param id - The id of the user to update
     * @param userDTO - The new values to change (account)
     */
    @RequestMapping(method = RequestMethod.PUT, value = "/users/{id}")
    public boolean putMoney(@PathVariable final int id, @RequestBody UserDTO userDTO) {
        Object [] parameters = {Integer.toString(id), userDTO};
        this.jmsProducer.sendPutMessage(parameters);
        return true;
    }

    /**
     * Delete - Delete an user
     * @param id - The id of the user to delete
     */
    @DeleteMapping("/api/user/{id}")
    public void delete(@PathVariable final int id) {
        userService.deleteUser(id);
    }
}
