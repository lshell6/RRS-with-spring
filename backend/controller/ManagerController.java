package com.rewards.backend.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.util.Base64;
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
import com.rewards.backend.dto.ManagerInfoDto;
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
	public void postManager(@RequestBody ManagerDto managerDto) {
		// we use JpaRepository Interface
		String str = new String(Base64.getDecoder().decode(managerDto.getEncodedCredentials()));
		String username = str.split("@%")[0];
		String password = str.split("@%")[1];
		Manager manager = new Manager();
		manager.setName(managerDto.getName());
		manager.setUsername(username);
		manager.setPasswordLastReset(LocalDate.now());
		manager.setPassword(passwordEncoder.encode(password));
		manager.setSecurityQuestion(managerDto.getSecurityQuestion());
		manager.setSecurityAnswer(managerDto.getSecurityAnswer());
		managerRepository.save(manager);
	}
	//login
	@GetMapping("/managerLogin")
	public ManagerInfoDto login(Principal principal) {
		String username = principal.getName();
		Manager info = managerRepository.getByManagerUsername(username);
		ManagerInfoDto dto = new ManagerInfoDto();
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
	//Verify Security Question
	@GetMapping("/verify-security-answer")
	public boolean verifySecurityQuestion(@PathVariable("encodedText") String encodedText) {
		boolean status = false;
		String str = new String(Base64.getDecoder().decode(encodedText));
		String[] sarr = str.split("--");
		String username = sarr[0];
		String answer = sarr[1];
		Manager manager = managerRepository.getByManagerUsername(username);
		if(manager.getSecurityAnswer().equalsIgnoreCase(answer)) {
			status = true;
		}
		return status;
	}
	//Reset Password
	@PutMapping("/manager/reset-password/{encodedText}")
	public void resetPassword(@PathVariable("encodedText") String encodedText) {
		String str = new String(Base64.getDecoder().decode(encodedText)); 
		String[] sarr = str.split("--");
		String username = sarr[0]; 
		String password = sarr[1];
		String encodedPassword = this.passwordEncoder.encode(password);
		managerRepository.resetPassword(username, encodedPassword, LocalDate.now());	
	}
	
}
