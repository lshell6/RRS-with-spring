package com.rewards.backend.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rewards.backend.dto.ManagerDto;
import com.rewards.backend.model.Employee;
import com.rewards.backend.model.Manager;
import com.rewards.backend.repository.ManagerRepository;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
public class ManagerController {
	@Autowired
	private ManagerRepository managerRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	//post
	@PostMapping("/manager")
	public Manager postManager(@RequestBody Manager manager) {
		// we use JpaRepository Interface
		Manager info = managerRepository.getByManagerUsername(manager.getUsername());
		if(info != null)
			throw new RuntimeException("Invalid Username");
		String password = manager.getPassword();
		password = passwordEncoder.encode(password);
		manager.setPassword(password);
		return managerRepository.save(manager);
	}
	//login
	@GetMapping("/managerLogin")
	public ManagerDto login(Principal principal) {
		String username = principal.getName();
		Manager info = managerRepository.getByManagerUsername(username);
		ManagerDto dto = new ManagerDto();
		dto.setId(info.getId());
		dto.setName(info.getName());
		dto.setUsername(info.getUsername());
		return dto;
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
}
