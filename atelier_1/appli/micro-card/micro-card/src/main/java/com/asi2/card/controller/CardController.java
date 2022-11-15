package com.asi2.card.controller;

import com.asi2.card.jms.JmsProducer;
import com.asi2.card.model.Card;
import com.asi2.card.tools.CardMapper;
import com.asi2.common.model.CardDTO;
import com.asi2.common.model.TransactionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CardController {
	@Autowired
	private final CardService cService;
	@Autowired
	private JmsProducer jmsProducer;

	public CardController(CardService cService) {
		this.cService = cService;
	}

	@RequestMapping(value = "/card", method = RequestMethod.POST)
	public CardDTO addCard(@RequestBody CardDTO cardDto) {
		//cService.addCard(cardDto);
		this.jmsProducer.sendCreationMessage(cardDto);
		return cardDto;
	}

	@RequestMapping(value = "/cards/{id}", method = RequestMethod.GET)
	public Card getCard(@PathVariable int id) {
		return cService.getCard(id);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/cards")
	public Iterable<Card> getCards() {
		return cService.getCards();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/cardsUser/{id}")
	public Iterable<Card> getCardsUser(@PathVariable int id) {
		return cService.getCardsUser(id);
	}

	@RequestMapping(method = RequestMethod.PUT, value="/card/{id}")
	public CardDTO modifyCard(@RequestBody CardDTO newCard, @PathVariable Integer id) {
		return this.cService.modifyCard(id, newCard);
	}

}