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

import com.rewards.backend.model.Manager;
import com.rewards.backend.repository.ManagerRepository;

@Service
public class ManagerDetailsService {
	@Autowired
	private ManagerRepository managerRepositroy;

	public UserDetails loadManagerByUsername(String username) throws UsernameNotFoundException {
		Manager managerInfo = managerRepositroy.getByManagerUsername(username);
		if (managerInfo == null)
			throw new UsernameNotFoundException("Manager ID is invalid");
		List<GrantedAuthority> list = new ArrayList<>();
		SimpleGrantedAuthority sga = new SimpleGrantedAuthority(managerInfo.getName());
		list.add(sga);
		User manager = new User(managerInfo.getUsername(), managerInfo.getPassword(), list);
		return manager;
	}
}
