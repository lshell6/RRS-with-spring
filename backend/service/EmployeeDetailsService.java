package com.rewards.backend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.rewards.backend.model.Employee;
import com.rewards.backend.repository.EmployeeRepository;

public abstract class EmployeeDetailsService implements UserDetailsService{
	@Autowired
	private EmployeeRepository employeeRepository;
	
	public Employee loadEmployeeByUsername(String username) throws UsernameNotFoundException {
		Employee employee = employeeRepository.getByUsername(username);
		//convert UserInfo(User from DB) in UserDetails
		if(employee == null)
			throw new UsernameNotFoundException("Username invalid!!!");
		/*
		 * user role into List<GrantedAuthority>
		 */
		List<GrantedAuthority> list = new ArrayList<>();
		SimpleGrantedAuthority sga = new SimpleGrantedAuthority(employee.getName()); 
		list.add(sga);
		
		employee =new Employee(employee.getUsername(), employee.getPassword(),list );
		return employee;
	}
}
