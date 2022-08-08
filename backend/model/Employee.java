package com.rewards.backend.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "employee")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String username;
	
	@Column(nullable = false)
	private String password;
	
	@Column(columnDefinition = "integer default 0")
	private Integer current_points;
	
	@Column(columnDefinition = "integer default 0")
	private Integer total_points;

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Employee(Long id, String name, String username, String password, Integer current_points,
			Integer total_points) {
		super();
		this.id = id;
		this.name = name;
		this.username = username;
		this.password = password;
		this.current_points = current_points;
		this.total_points = total_points;
	}

	public Employee(String username2, String password2, List<GrantedAuthority> list) {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getCurrent_points() {
		return current_points;
	}

	public void setCurrent_points(Integer current_points) {
		this.current_points = current_points;
	}

	public Integer getTotal_points() {
		return total_points;
	}

	public void setTotal_points(Integer total_points) {
		this.total_points = total_points;
	}
	
	
}
