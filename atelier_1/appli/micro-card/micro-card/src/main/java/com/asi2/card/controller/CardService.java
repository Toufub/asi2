package com.asi2.card.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.asi2.card.tools.CardMapper;
import com.asi2.common.model.CardDTO;
import com.asi2.common.model.TransactionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.asi2.card.model.Card;

@Service
public class CardService {
	@Autowired
	private final CardRepository cRepo;
	
	public CardService(CardRepository cRepo) {
		this.cRepo=cRepo;
	}

	public int addCard(CardDTO c) {
		Card createdCard=cRepo.save(CardMapper.FromDTOToTransaction(c));
		return createdCard.getId();
	}
	public CardDTO modifyCard(Integer id, CardDTO newCard) {
		return this.cRepo.findById(id)
				.map(card -> {
					card.setPrice(newCard.getPrice());
					card.setUserId(newCard.getUserId());
					return CardMapper.FromCardToDTO(this.cRepo.save(card));
				})
				.orElseGet(() -> {
					return null;
				});
	}

	public Card getCard(int id) {
		Optional<Card> cOpt = cRepo.findById(id);
		if (cOpt.isPresent()) {
			return cOpt.get();
		}else {
			return null;
		}
	}

	public Iterable<Card> getCards() {
		return cRepo.findAll();
	}

	public List<Card> getCardsUser(int id) {
		Iterable<Card> tmpCards = cRepo.findAll();
		List<Card> cards = new ArrayList<>();
		System.out.println(id);

		for (Card card:tmpCards) {
			if(card.getUserId() == id){
				cards.add(card);
			}
		}
		return cards;
	}




}
