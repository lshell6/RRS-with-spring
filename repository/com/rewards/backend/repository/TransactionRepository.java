package com.rewards.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rewards.backend.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{
	@Query("select t from Transaction t")
	List<Transaction>findAll();
	@Query("select numOfItems from Transaction t where t.employee_username?=Harry")
	int countItems();
	@Query("select t from Transaction t where t.employee_username=?Harry")
	List<Transaction> getTransactionByEmployee(String username);
	
}
