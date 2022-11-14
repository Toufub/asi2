package com.asi2.user.controller;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.asi2.user.model.User;

import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<User, UUID> {
    @Query("select u from User u where u.login = ?1")
    public User findByUsername(String username);
}
