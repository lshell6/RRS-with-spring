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
	
	
//login

@GetMapping("manager/login")

public ManagerInfoDto login(Principal principal) {

String username = principal.getName();

Manager info = managerRepository.getByUsername(username);

ManagerInfoDto dto = new ManagerInfoDto();

dto.setId(info.getId());

dto.setName(info.getName());

dto.setUsername(info.getUsername());

return dto;

}

//get manager by username

@GetMapping("manager/username")

public ManagerEditDto getManagerByUsername(Principal principal) {

Manager info = managerRepository.getByUsername(principal.getName());

ManagerEditDto dto = new ManagerEditDto(info.getId(), info.getName(), info.getSecurityQuestion(), info.getSecurityAnswer());

return dto;

}

//update profile

@PutMapping("manager/profile")

public void profileEdit(Principal principal, @RequestBody ManagerDto dto) {

String username = principal.getName();

managerRepository.updateProfile(username, dto.getName(), dto.getSecurityQuestion(), dto.getSecurityAnswer());

}

//getting manager info by username

@GetMapping("manager/security/info/{username}")

public ManagerEditDto getManagerInfo(@PathVariable("username") String username) {

Manager info = managerRepository.getByUsername(username);

ManagerEditDto dto = new ManagerEditDto(info.getId(), info.getName(), "", info.getSecurityQuestion());

return dto;

}

//validate security answer

@GetMapping("manager/validate-security-answer/{encodedText}")

public boolean verifySecurityAnswer(@PathVariable("encodedText") String encodedText) {

boolean status = false;

String str = new String(Base64.getDecoder().decode(encodedText));

String[] arr = str.split("--");

String username = arr[0];

String answer = arr[1];

Manager info = managerRepository.getByUsername(username);

if(info.getSecurityAnswer().equalsIgnoreCase(answer)) {

status = true;

}

return status;

}

//reset-password

@PutMapping("manager/reset-password/{encodedText}")

public void resetPassword(@PathVariable("encodedText") String encodedText) {

boolean status = false;

String str = new String(Base64.getDecoder().decode(encodedText));

String[] strings = str.split("--");

String username = strings[0];

String password = strings[1];

managerRepository.resetPassword(username, password, LocalDate.now());

}

	
}
