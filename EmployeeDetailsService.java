package com.rewards.backend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rewards.backend.model.Employee;
import com.rewards.backend.repository.EmployeeRepository;

@Service
public class EmployeeDetailsService {
	@Autowired
	private EmployeeRepository employeeRepository;

	public UserDetails loadEmployeeByUsername(String username) throws UsernameNotFoundException {
		Employee employeeInfo = employeeRepository.getByEmployeeUsername(username);
		if (employeeInfo == null)
			throw new UsernameNotFoundException("Employee ID is invalid");
		List<GrantedAuthority> list = new ArrayList<>();
		SimpleGrantedAuthority sga = new SimpleGrantedAuthority(employeeInfo.getName());
		list.add(sga);
		User employee = new User(employeeInfo.getUsername(), employeeInfo.getPassword(), list);
		return employee;
	}
}
