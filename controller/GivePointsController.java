package com.rewards.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rewards.backend.model.Employee;
import com.rewards.backend.model.GivePoints;
import com.rewards.backend.model.Manager;
import com.rewards.backend.repository.EmployeeRepository;
import com.rewards.backend.repository.GivePointsRepository;
import com.rewards.backend.repository.ManagerRepository;

@RestController
public class GivePointsController {
	
	@Autowired
	private GivePointsRepository givePointsRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private ManagerRepository managerRepository;
	
	@PostMapping("/manager/employee/{mid}/{eid}/{pts}")
	public GivePoints givePointsToEmployee(@PathVariable("mid") Long mid,
									 @PathVariable("eid") Long eid,
									 @PathVariable("pts") int pts,
									 @RequestBody GivePoints gp) {
		gp.setPoints(pts);
		// validate manager and employee
		Optional<Manager> optionalM = managerRepository.findById(mid);
		if(!optionalM.isPresent()) 
			throw new RuntimeException("Invalid Manager ID Given");
		
		Optional<Employee> optionalE = employeeRepository.findById(eid);
		if(!optionalE.isPresent()) 
			throw new RuntimeException("Invalid Employee ID Given");
		// get manager and employee
		Manager m = optionalM.get();
		Employee e = optionalE.get();

		// update points value added to employee
		int tempCurrent = e.getCurrent_points();
		int tempTotal = e.getTotal_points();
		
		tempCurrent += pts;
		tempTotal += pts;
		
		e.setCurrent_points(tempCurrent);
		e.setTotal_points(tempTotal);
		
		// set updated manager and employee with updated point values for employee
		gp.setManager(m);
		gp.setEmployee(e);
		
		return givePointsRepository.save(gp);
	}
	
	@GetMapping("/employee/manager/{mid}")
	public List<Employee> getEmployeeByManagerId(@PathVariable("mid") Long mid){
		Optional<Manager> optionalM = managerRepository.findById(mid);
		if(!optionalM.isPresent())
			throw new RuntimeException("Manager ID invalid");
		
		List<Employee> list = givePointsRepository.getEmployeeByManagerId(mid);
		return list; 
	}
	
	@GetMapping("/manager/employee/{eid}")
	public List<Manager> getManagerByEmployeeId(@PathVariable("eid") Long eid){
		Optional<Employee> optionalE = employeeRepository.findById(eid);
		if(!optionalE.isPresent()) 
			throw new RuntimeException("Employee ID is invalid");
		
		List<Manager> list = givePointsRepository.getManagerByEmployeeId(eid);
		return list;
	}
	
	
	
	
}
