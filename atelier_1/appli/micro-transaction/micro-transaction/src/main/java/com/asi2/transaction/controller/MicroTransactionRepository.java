package com.asi2.transaction.controller;

import com.asi2.transaction.model.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface MicroTransactionRepository extends CrudRepository<Transaction, Integer> {

}
