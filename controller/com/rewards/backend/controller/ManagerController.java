package com.rewards.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rewards.backend.model.Employee;
import com.rewards.backend.model.Manager;
import com.rewards.backend.repository.ManagerRepository;

@RestController
public class ManagerController {
	@Autowired
	private ManagerRepository managerRepository;
	
	//post
	@PostMapping("/manager")
	public void postManager(@RequestBody Manager manager) {
		// we use JpaRepository Interface
		managerRepository.save(manager);
	} 
	//find all
	@GetMapping("/manager")
	public List<Manager> getAllManagers(){
		return managerRepository.findAll();
	}
	//find by id
	@GetMapping("/manager/single/{id}")
	public Manager getSingleManagerById(@PathVariable("id") Long id) {
		Optional<Manager> optional = managerRepository.findById(id);
		if(optional.isPresent())
			return optional.get();
		throw new RuntimeException("ID is invalid");
	}
	//delete by id
	@DeleteMapping("/manager/{id}")
	public void deleteManager(@PathVariable("id") Long id) {
		managerRepository.deleteById(id);
	}
	//update
	@PutMapping("/manager/{id}")
	public Manager updateManager(@PathVariable("id") Long id,
			@RequestBody Manager newManager) {
		Optional<Manager> optional = managerRepository.findById(id);
		if(optional.isPresent()) {
			Manager existingManager = optional.get();
			existingManager.setName(newManager.getName());
			existingManager.setUsername(newManager.getUsername());
			existingManager.setPassword(newManager.getPassword());
			return managerRepository.save(existingManager);
		}
		else
			throw new RuntimeException("ID is invalid");
	}
	//search by emp name
	
	//giving pts to emp
	
}
