package com.asi2.auth.controller;

import com.asi2.common.model.UserDTO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import javax.servlet.http.HttpServletResponse;

@RestController
public class MicroARestCrt {
	private final MicroAuthService authService;
	
	public MicroARestCrt(MicroAuthService aService) {
		this.authService=aService;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/api/auth/login")
	public UserDTO login(@RequestBody LoginDto loginForm, HttpServletResponse response) {
		/*UsersDto user = usersService.checkLogin(loginForm);
		if (user == null){
			throw new AuthError("bad_login");
		}
		String jwt = Jwts.builder()
				.setIssuer("Aterlier3")
				.setIssuedAt(Date.from(Instant.now()))
				.setExpiration(Date.from(Instant.now().plus(6, ChronoUnit.HOURS)))
				.signWith(SignatureAlgorithm.HS256, AuthentificationService.getSecret())
				.setSubject(user.getUsername()).compact();

		Cookie cookie = new Cookie("JWT", jwt);
		cookie.setPath("/");
		response.addCookie(cookie);
		loginForm.setPassword(null);
		return loginForm;
		 */
	}
}
