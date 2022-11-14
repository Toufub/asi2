package com.asi2.user.controller;

import org.springframework.data.repository.CrudRepository;

import com.asi2.user.model.User;

public interface MicroUserRepository extends CrudRepository<User, Integer> {

}
