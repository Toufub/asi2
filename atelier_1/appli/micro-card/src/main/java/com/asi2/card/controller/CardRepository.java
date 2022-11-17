package com.asi2.card.controller;

import org.springframework.data.repository.CrudRepository;

import com.asi2.card.model.Card;

import java.util.List;

public interface CardRepository extends CrudRepository<Card, Integer> {
    public List<Card> findByName(String name);
}
