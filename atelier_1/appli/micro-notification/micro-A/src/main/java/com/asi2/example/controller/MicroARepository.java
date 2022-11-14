package com.asi2.card.controller;

import org.springframework.data.repository.CrudRepository;

import com.asi2.card.model.ACollection;

public interface MicroARepository extends CrudRepository<ACollection, Integer> {

}
