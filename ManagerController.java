package com.rewards.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rewards.backend.model.Manager;
import com.rewards.backend.repository.ManagerRepository;

@RestController
public class ManagerController {
	@Autowired
	private ManagerRepository managerRepository;
	
	@PostMapping("/employee")
	public void postManager(@RequestBody Manager manager) {
		// we use JpaRepository Interface
		managerRepository.save(manager);
	} 
}
