package com.asi2.transaction.controller;

import java.util.ArrayList;
import java.util.List;
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
/*
	public Integer addBImg(BImgDTO bDTO) {
		BImg b= BImgMapper.FromDTOTOBImg(bDTO);

		BImg bInDb=bRepo.save(b);
		return bInDb.getId();
	}

	public BImgDTO getRand() {
		List<BImgDTO> bDTOList =getAllBImg();
		int randomNum = ThreadLocalRandom.current().nextInt(0, bDTOList.size());
		return bDTOList.get(randomNum);
	}
*/
}
