package com.asi2.user.tools;

import com.asi2.user.model.UserDTO;

import java.util.Optional;

import com.asi2.user.model.User;

public class UserMapper {
	public static UserDTO UserToDTO(User a) {
		return new UserDTO(a.GetId(), a.GetName(), a.GetSurname(), a.GetPassword(), a.GetMoney());
	}

	public static User DTOtoUser(UserDTO aDTO) {
		User a = new User(aDTO.GetName(), aDTO.GetSurname(), aDTO.GetPassword(), aDTO.GetMoney());
		a.SetId(aDTO.GetId());
		return a;
	}
}

