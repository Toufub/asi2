package com.asi2.transaction.controller;

import java.util.List;

import com.asi2.transaction.model.Transaction;
import org.springframework.web.bind.annotation.*;

import com.asi2.transaction.model.TransactionDTO;

@RestController
public class MicroTransactionRestCrt {
	private final MicroTransactionService transactionService;

	public MicroTransactionRestCrt(MicroTransactionService transactionService) {
		this.transactionService = transactionService;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/transactions")
	public List<TransactionDTO> getTransactionList() {
		return this.transactionService.getAllTransactions();
	}

	@RequestMapping(method = RequestMethod.POST, value = "/transactions")
	public Integer addTransaction(@RequestBody TransactionDTO transactionDTO) {
		return this.transactionService.addTransaction(transactionDTO);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/transactions/{id}")
	public TransactionDTO getTransaction(@PathVariable Integer id) {
		return this.transactionService.getTransaction(id);
	}

	@RequestMapping(method = RequestMethod.PUT, value="/transactions/{id}")
	public TransactionDTO replaceTransaction(@RequestBody Transaction newTransaction, @PathVariable Integer id) {
		return this.transactionService.modifyTransaction(newTransaction, id);
	}
}
