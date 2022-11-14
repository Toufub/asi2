package com.asi2.user.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import com.asi2.user.model.User;
import com.asi2.user.model.UserDTO;
import com.asi2.user.tools.UserMapper;

@Service
public class MicroUserService {
	private final MicroUserRepository aRepo;

	public MicroUserService(MicroUserRepository aRepo) {
		this.aRepo = aRepo;
	}

	public List<UserDTO> getAllACollection() {
		List<UserDTO> aDtoList = new ArrayList<UserDTO>();
		Iterable<User> aList = aRepo.findAll();
		for (User a : aList) {
			aDtoList.add(UserMapper.FromACollectionToDTO(a));
		}
		return aDtoList;
	}

	public String addAcollection(UserDTO aDTO) {
		User a = UserMapper.FromDTOTOACollection(aDTO);
		User aInDb = aRepo.save(a);
		return aInDb.GetId();
	}

}
