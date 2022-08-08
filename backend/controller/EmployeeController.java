package com.rewards.backend.controller;


import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rewards.backend.model.Employee;
import com.rewards.backend.repository.EmployeeRepository;
import com.rewards.backend.dto.EmployeeDto;

@RestController
public class EmployeeController {
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private PasswordEncoder passwordEncoder; 
	
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
	
	//employee login
	@GetMapping("employee/login") //username/password
	public EmployeeDto login(Principal principal) {
		String username = principal.getName();
		Employee emp = employeeRepository.getByUsername(username);
		EmployeeDto dto = new EmployeeDto();
		//dto.setId(emp.getId());
		dto.setName(emp.getName());
		//dto.setUsername(emp.getUsername());
		dto.setCurrent_points(emp.getCurrent_points());
		dto.setTotal_points(emp.getTotal_points());
		return dto; 
	}
	
}
