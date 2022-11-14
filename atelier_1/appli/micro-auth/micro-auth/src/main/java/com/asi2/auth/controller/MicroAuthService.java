package com.asi2.auth.controller;

import com.asi2.auth.model.UserDTO;
import org.springframework.stereotype.Service;

import com.asi2.auth.a.model.UserDTO;
import com.asi2.auth.a.model.ChuckApiModel;
import com.asi2.auth.b.model.BImgDTO;
import com.asi2.auth.model.ACollection;
import com.asi2.auth.tools.ACollectionMapper;
import com.asi2.auth.tools.ApiCallerTool;

@Service
public class MicroAuthService {

	public MicroAuthService() {

	}

	public UserDTO checkLogin(String username, String pass){
		return null;
	}

	public UsersDto findByUsername(String username){
		return null;
	}

}
