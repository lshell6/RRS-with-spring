package com.rewards.backend.dto;

public class EmployeeDto {
	private Long id;
	private String name;
	private String username;
	private Integer current_points;
	private Integer total_points;
	
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
