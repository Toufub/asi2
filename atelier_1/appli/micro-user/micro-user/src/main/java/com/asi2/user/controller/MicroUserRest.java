package com.asi2.user.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.asi2.user.model.UserDTO;

@RestController
public class MicroUserRest {
	private final MicroUserService aService;

	public MicroUserRest(MicroUserService aService) {
		this.aService = aService;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/acollection")
	public List<UserDTO> getACollectionList() {
		return this.aService.getAllACollection();
	}

	@RequestMapping(method = RequestMethod.POST, value = "/acollection")
	private String addACollection(@RequestBody UserDTO user) {
		return aService.addAcollection(user);
	}
}
