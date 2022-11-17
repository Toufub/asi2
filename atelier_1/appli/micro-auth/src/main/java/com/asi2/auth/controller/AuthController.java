package com.asi2.auth.controller;

import com.asi2.auth.exceptions.AuthError;
import com.asi2.common.model.LoginDTO;
import com.asi2.common.model.UserDTO;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@RestController
public class AuthController {
	private final UserService userService;

	public AuthController(AuthService aService, UserService uService) {
		this.userService = uService;
	}
	@RequestMapping(method = RequestMethod.POST, value = "/auth")
	public LoginDTO login(@RequestBody LoginDTO loginForm, HttpServletResponse response) {
		UserDTO user = userService.checkLogin(loginForm);
		if (user == null){
			throw new AuthError("bad_login");
		}
		Cookie cookie = new Cookie("login", user.getLogin());
		cookie.setPath("/");
		response.addCookie(cookie);
		loginForm.setPassword(null);
		return loginForm;
	}

}
