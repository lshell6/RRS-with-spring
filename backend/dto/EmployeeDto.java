package com.rewards.backend.dto;

public class EmployeeDto {
	private String name;
	private int current_points;
	private int total_points;
	private String securityQuestion;
	private String securityAnswer;
	private String encodedCredentials;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCurrent_points() {
		return current_points;
	}
	public void setCurrent_points(int current_points) {
		this.current_points = current_points;
	}
	public int getTotal_points() {
		return total_points;
	}
	public void setTotal_points(int total_points) {
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
	public String getEncodedCredentials() {
		return encodedCredentials;
	}
	public void setEncodedCredentials(String encodedCredentials) {
		this.encodedCredentials = encodedCredentials;
	}
	
}
