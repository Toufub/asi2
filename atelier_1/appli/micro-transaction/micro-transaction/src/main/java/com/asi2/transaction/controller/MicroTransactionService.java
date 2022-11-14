package com.asi2.transaction.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Service;

import com.asi2.transaction.model.TransactionDTO;
import com.asi2.transaction.model.Transaction;
import com.asi2.transaction.tools.TransactionMapper;
@Service
public class MicroTransactionService {
	private final MicroTransactionRepository tRepo;
	
	public MicroTransactionService(MicroTransactionRepository bRepo) {
		this.tRepo=bRepo;
	}

	public List<TransactionDTO> getAllTransactions() {
		List<TransactionDTO> transactionDtoList=new ArrayList<TransactionDTO>();
		Iterable<Transaction> transactionList = tRepo.findAll();
		for(Transaction transaction:transactionList) {
			transactionDtoList.add(TransactionMapper.FromTransactionToDTO(transaction));
		}
		return transactionDtoList;
	}

	public TransactionDTO getTransaction(Integer id) {
		Optional<Transaction> transaction = tRepo.findById(id);
		if(transaction.isPresent()) {
			return TransactionMapper.FromTransactionToDTO(transaction.get());
		} else {
			return null;
		}
	}

	public Integer addTransaction(TransactionDTO transactionDTO) {
		Transaction t= TransactionMapper.FromDTOToTransaction(transactionDTO);
		Transaction tInDb=tRepo.save(t);
		return tInDb.getId();
	}

	public TransactionDTO modifyTransaction(Transaction newTransaction, Integer id) {
		return this.tRepo.findById(id)
				.map(transaction -> {
					transaction.setBuyerId(newTransaction.getBuyerId());
					transaction.setTransactionDate(newTransaction.getTransactionDate());
					return TransactionMapper.FromTransactionToDTO(this.tRepo.save(transaction));
				})
				.orElseGet(() -> {
					return null;
				});
	}
}
