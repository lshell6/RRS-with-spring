package com.rewards.backend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.rewards.backend.model.Employee;
import com.rewards.backend.model.Manager;
import com.rewards.backend.repository.ManagerRepository;

public abstract class ManagerDetailsService implements UserDetailsService{
	@Autowired
	private ManagerRepository managerRepository;
	
	public Manager loadManagerByUsername(String username) throws UsernameNotFoundException {
		Manager manager = managerRepository.getByUsername(username);
		//convert UserInfo(User from DB) in UserDetails
		if(manager == null)
			throw new UsernameNotFoundException("Username invalid!!!");
		/*
		 * user role into List<GrantedAuthority>
		 */
		List<GrantedAuthority> list = new ArrayList<>();
		SimpleGrantedAuthority sga = new SimpleGrantedAuthority(manager.getName()); 
		list.add(sga);
		
		manager =new Manager(manager.getUsername(), manager.getPassword(),list );
		return manager;
	}
}
