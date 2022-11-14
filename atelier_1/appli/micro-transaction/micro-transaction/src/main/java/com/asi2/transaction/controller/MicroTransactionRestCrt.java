package com.asi2.transaction.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.asi2.transaction.model.TransactionDTO;

@RestController
public class MicroTransactionRestCrt {
	private final MicroTransactionService transactionService;

	public MicroTransactionRestCrt(MicroTransactionService transactionService) {
		this.transactionService = transactionService;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/transactions")
	public List<TransactionDTO> getTransactionsList() {
		return this.transactionService.getAllTransactions();
	}
}
