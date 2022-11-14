package com.asi2.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.asi2.user.model.User;
import com.asi2.user.model.UserDTO;
import com.asi2.user.tools.UserMapper;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    //private AuthService authService = new AuthService();

    /**
     * Read - Get one user
     * @param id The id of the user
     * @return An User object full filled
     */
    @GetMapping("/api/user/{id}")
    public UserDTO getById(@PathVariable final int id) {
        return convertToDTO(userService.getUser(id));
    }

    /**
     * Read - Get all users
     * @return A list of User object full filled
     */
    @GetMapping("/api/user")
    public Iterable<UserDTO> getAll() {
        List<User> userList = (List<User>) userService.getAllUsers();
        List<UserDTO> userDTOList = null;
        for (User user : userList) {
            userDTOList.add(convertToDTO(user));
        }
        return userDTOList;
    }

    /**
     * Create - Add a new user
     * @param userDTO An object user
     * @return The user object saved
     */
    @PostMapping("/api/user")
    public UserDTO create(@RequestBody UserDTO userDTO) throws ParseException {
        User user = convertToEntity(userDTO);
        User userCreate = userService.createUser(user);
        return convertToDTO(userCreate);
    }

    /*
    @RequestMapping(method = RequestMethod.GET, value = "/api/users/connected")
    public User getConnectedUser(@CookieValue(value = "JWT") String jwt){
        String id = authService.getId(jwt);

        User user = userService.getUser(UUID.fromString(id)).get();
        return user;
    }
    */

    /*
    @RequestMapping(method = RequestMethod.POST, value = "/api/auth/login")
    public UserDTO login(@RequestBody UserDTO userDTO, HttpServletResponse response) throws ParseException {
        User tempUser = convertToEntity(userDTO);
        User user = userService.checkLogin(tempUser.getName(), tempUser.getPassword());
        if (user == null){
            return null;
        }
        String jwt = Jwts.builder()
                .setIssuer("Aterlier3")
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plus(6, ChronoUnit.HOURS)))
                .signWith(SignatureAlgorithm.HS256, AuthService.getSecret())
                .setSubject(user.getId().toString()).compact();

        Cookie cookie = new Cookie("JWT", jwt);
        cookie.setPath("/");

        response.addCookie(cookie);
        return convertToDto(user);
    }

    @GetMapping(value = "/api/auth")
    public void validate_token(@CookieValue(name = "JWT") String JWT, HttpServletResponse response){
        boolean is_jwt_correct = authService.checkJwt(JWT);

        if(!is_jwt_correct){
            Cookie cookie = new Cookie("JWT", null);
            cookie.setPath("/");
            response.addCookie(cookie);
        }
        response.setStatus(HttpStatus.NO_CONTENT.value());

    }
    */

    /**
     * Put - Money
     * @param id - The id of the user to update
     * @param money - The new balance value
     */
    @PutMapping("/api/user/{id}")
    public UserDTO putMoney(@PathVariable final int id, @RequestBody double money) {
        return convertToDTO(userService.putUser(id, money));
    }

    /**
     * Delete - Delete an user
     * @param id - The id of the user to delete
     */
    @DeleteMapping("/api/user/{id}")
    public void delete(@PathVariable final int id) {
        userService.deleteUser(id);
    }

    private User convertToEntity(UserDTO userDTO) throws ParseException {
        User user = UserMapper.DTOtoUser(userDTO);
        return user;
    }

    private UserDTO convertToDTO(User user) {
        UserDTO userDTO = UserMapper.UserToDTO(user);
        return userDTO;
    }
}
