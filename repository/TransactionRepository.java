package com.rewards.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rewards.backend.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{

}
