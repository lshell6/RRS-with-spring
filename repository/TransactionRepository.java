package com.rewards.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rewards.backend.model.transaction;

public interface TransactionRepository extends JpaRepository<transaction, Long>{

}
