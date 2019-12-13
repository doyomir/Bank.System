package com.Bank.System.repository;

import com.Bank.System.model.operation.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends CrudRepository <Transaction, Integer> {
}
