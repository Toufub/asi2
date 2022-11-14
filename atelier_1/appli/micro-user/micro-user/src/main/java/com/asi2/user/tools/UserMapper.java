package com.asi2.user.tools;

import com.asi2.user.model.UserDTO;

import java.util.Optional;

import com.asi2.user.model.User;

public class UserMapper {
	public static UserDTO UserToDTO(User a) {
		return new UserDTO(a.getId(), a.getLastName(), a.getSurName(), a.getLogin(), a.getPwd(), a.getAccount());
	}

	public static User DTOtoUser(UserDTO aDTO) {
		User a = new User(aDTO.getId(), aDTO.getLastName(), aDTO.getSurName(), aDTO.getLogin(), aDTO.getPwd(), aDTO.getAccount());
		a.setId(aDTO.getId());
		return a;
	}
}

