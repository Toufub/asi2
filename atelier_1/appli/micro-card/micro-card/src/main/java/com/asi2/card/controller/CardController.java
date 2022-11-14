package com.asi2.card.controller;

import com.asi2.card.model.Card;
import com.asi2.card.model.CardDto;
import com.asi2.card.tools.CardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CardController {
	@Autowired
	private final CardService cService;

	public CardController(CardService cService) {
		this.cService = cService;
	}

	@RequestMapping(value = "/api/cards", method = RequestMethod.POST)
	public Card addCard(@RequestBody CardDto cardDto) {
		Card card = CardMapper.INSTANCE.toModel(cardDto);
		cService.addCard(card);
		return card;
	}

	@RequestMapping(value = "/api/cards/{id}", method = RequestMethod.GET)
	public Card getCard(@PathVariable int id) {
		return cService.getCard(id);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/api/cards")
	public Iterable<Card> getCards() {
		return cService.getCards();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/api/cardsUser/{id}")
	public Iterable<Card> getCardsUser(@PathVariable int id) {
		return cService.getCardsUser(id);
	}

}