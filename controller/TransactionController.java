package com.rewards.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.rewards.backend.repository.TransactionRepository;

@RestController
public class TransactionController {
	@Autowired
	private TransactionRepository transactionRepository;
	
}
