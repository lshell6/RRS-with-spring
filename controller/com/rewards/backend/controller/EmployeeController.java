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
import com.rewards.backend.repository.EmployeeRepository;

@CrossOrigin(origins = {"http://localhost:4200/"})
@RestController
public class EmployeeController {
	@Autowired
	private EmployeeRepository employeeRepository;
	
	//post
	@PostMapping("/employee")
	public void postEmployee(@RequestBody Employee employee) {
		// we use JpaRepository Interface
		employeeRepository.save(employee);
	}
	//find all
	@GetMapping("/employee")
	public List<Employee> getAllEmployees(){
		return employeeRepository.findAll();
	}
	//find by id
	@GetMapping("/employee/single/{id}")
	public Employee getSingleEmployeeById(@PathVariable("id") Long id) {
		Optional<Employee> optional = employeeRepository.findById(id);
		if(optional.isPresent())
			return optional.get();
		throw new RuntimeException("ID is invalid");
	}
	//delete by id
	@DeleteMapping("/employee/{id}")
	public void deleteEmployee(@PathVariable("id") Long id) {
		employeeRepository.deleteById(id);
	}
	//update
	@PutMapping("/employee/{id}")
	public Employee updateEmployee(@PathVariable("id") Long id,
			@RequestBody Employee newEmployee) {
		Optional<Employee> optional = employeeRepository.findById(id);
		if(optional.isPresent()) {
			Employee existingEmployee = optional.get();
			existingEmployee.setName(newEmployee.getName());
			existingEmployee.setUsername(newEmployee.getUsername());
			existingEmployee.setPassword(newEmployee.getPassword());
			existingEmployee.setCurrent_points(newEmployee.getCurrent_points());
			existingEmployee.setTotal_points(newEmployee.getTotal_points());
			return employeeRepository.save(existingEmployee);
		}
		else
			throw new RuntimeException("ID is invalid");
	}
	
	
//login
@GetMapping("/login")
public EmployeeInfoDto login(Principal principal) {
String username = principal.getName();
Employee info = employeeRepository.getByUsername(username);
EmployeeInfoDto dto = new EmployeeInfoDto();
dto.setId(info.getId());
dto.setName(info.getName());
dto.setUsername(info.getUsername());
return dto;
}

//get employee by username
@GetMapping("/username")
public EmployeeEditDto getEmployeeByUsername(Principal principal) {
Employee info = employeeRepository.getByUsername(principal.getName());
EmployeeEditDto dto = new EmployeeEditDto(info.getId(), info.getName(), info.getSecurityQuestion(), info.getSecurityAnswer());
return dto;
}

//update profile
@PutMapping("/profile")
public void profileEdit(Principal principal, @RequestBody EmployeeDto dto) {
String username = principal.getName();
employeeRepository.updateProfile(username, dto.getName(), dto.getSecurityQuestion(), dto.getSecurityAnswer());
}

//getting employee info by username
@GetMapping("/security/info/{username}")
public EmployeeEditDto getEmployeeInfo(@PathVariable("username") String username) {
Employee info = employeeRepository.getByUsername(username);
EmployeeEditDto dto = new EmployeeEditDto(info.getId(), info.getName(), "", info.getSecurityQuestion());
return dto;
}

//validate security answer
@GetMapping("/validate-security-answer/{encodedText}")
public boolean verifySecurityAnswer(@PathVariable("encodedText") String encodedText) {
boolean status = false;
String str = new String(Base64.getDecoder().decode(encodedText));
String[] arr = str.split("--");
String username = arr[0];
String answer = arr[1];
Employee info = employeeRepository.getByUsername(username);
if(info.getSecurityAnswer().equalsIgnoreCase(answer)) {
status = true;
}
return status;
}

//reset-password
@PutMapping("reset-password/{encodedText}")
public void resetPassword(@PathVariable("encodedText") String encodedText) {
boolean status = false;
String str = new String(Base64.getDecoder().decode(encodedText));
String[] strings = str.split("--");
String username = strings[0];
String password = strings[1];
employeeRepository.resetPassword(username, password, LocalDate.now());
}

	
}
