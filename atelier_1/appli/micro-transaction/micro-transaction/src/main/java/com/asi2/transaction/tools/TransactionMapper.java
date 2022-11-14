package com.asi2.transaction.tools;

import com.asi2.common.model.TransactionDTO;
import com.asi2.transaction.model.Transaction;

public class TransactionMapper {

    public static TransactionDTO FromTransactionToDTO(Transaction transaction){
        TransactionDTO transactionDTO=new TransactionDTO();
        transactionDTO.setId(transaction.getId());
        transactionDTO.setTransactionDate(transaction.getTransactionDate());
        transactionDTO.setBuyerId(transaction.getBuyerId());
        transactionDTO.setCardId(transaction.getCardId());
        transactionDTO.setSellerId(transaction.getSellerId());
        transactionDTO.setPrice(transaction.getPrice());
        return transactionDTO;
    }

    public static Transaction FromDTOToTransaction(TransactionDTO transactionDTO) {
        Transaction transaction = new Transaction();
        transaction.setId(transactionDTO.getId());
        transaction.setTransactionDate(transactionDTO.getTransactionDate());
        transaction.setBuyerId(transactionDTO.getBuyerId());
        transaction.setCardId(transactionDTO.getCardId());
        transaction.setSellerId(transactionDTO.getSellerId());
        transaction.setPrice(transactionDTO.getPrice());
        return transaction;
    }

}
