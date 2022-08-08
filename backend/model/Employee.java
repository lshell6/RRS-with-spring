package com.rewards.backend.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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

	@Column(nullable = false)
	private String securityQuestion;
	
	@Column(nullable = false)
	private String securityAnswer;
	
	private LocalDate passwordLastReset;

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public Employee(Long id, String name, String username, String password, Integer current_points,
			Integer total_points, String securityQuestion, String securityAnswer, LocalDate passwordLastReset) {
		super();
		this.id = id;
		this.name = name;
		this.username = username;
		this.password = password;
		this.current_points = current_points;
		this.total_points = total_points;
		this.securityQuestion = securityQuestion;
		this.securityAnswer = securityAnswer;
		this.passwordLastReset = passwordLastReset;
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


	public String getSecurityQuestion() {
		return securityQuestion;
	}


	public void setSecurityQuestion(String securityQuestion) {
		this.securityQuestion = securityQuestion;
	}


	public String getSecurityAnswer() {
		return securityAnswer;
	}


	public void setSecurityAnswer(String securityAnswer) {
		this.securityAnswer = securityAnswer;
	}


	public LocalDate getPasswordLastReset() {
		return passwordLastReset;
	}


	public void setPasswordLastReset(LocalDate passwordLastReset) {
		this.passwordLastReset = passwordLastReset;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", username=" + username + ", password=" + password
				+ ", current_points=" + current_points + ", total_points=" + total_points + ", securityQuestion="
				+ securityQuestion + ", securityAnswer=" + securityAnswer + ", passwordLastReset=" + passwordLastReset
				+ "]";
	}	
	
}
