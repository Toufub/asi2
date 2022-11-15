package com.asi2.transaction.controller;

import java.util.List;

import com.asi2.transaction.jms.JmsProducer;
import org.springframework.web.bind.annotation.*;
import com.asi2.common.model.TransactionDTO;

@RestController
public class TransactionController {
	private final MicroTransactionService transactionService;
	private JmsProducer jmsProducer;

	public TransactionController(MicroTransactionService transactionService, JmsProducer jmsProducer) {
		this.transactionService = transactionService;
		this.jmsProducer = jmsProducer;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/transaction")
	public List<TransactionDTO> getTransactionList() {
		return this.transactionService.getAllTransactions();
	}

	@RequestMapping(method = RequestMethod.POST, value = "/sell")
	public TransactionDTO addTransaction(@CookieValue(name = "login") String login,
										 @RequestBody TransactionDTO transactionDTO) {
		this.jmsProducer.sendCreationMessage(transactionDTO, login);
		return transactionDTO;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/transaction/{id}")
	public TransactionDTO getTransaction(@PathVariable Integer id) {
		return this.transactionService.getTransaction(id);
	}

	@RequestMapping(method = RequestMethod.PUT, value="/transaction/{id}")
	public boolean modifyTransaction(@CookieValue(name = "login") String login,
									 @RequestBody TransactionDTO newTransaction, @PathVariable Integer id) {
		Object [] parameters = {Integer.toString(id), newTransaction, login};
		this.jmsProducer.sendPutMessage(id, newTransaction, login);
		return true;
		//return this.transactionService.modifyTransaction(id, newTransaction);
	}
}
