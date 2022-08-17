package com.rewards.backend.dto;

public class ManagerDto {
	private Long id;
	private String name;
	private String encodedCredentials;
	private String securityQuestion;
	private String securityAnswer;
	
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
	public String getEncodedCredentials() {
		return encodedCredentials;
	}
	public void setEncodedCredentials(String encodedCredentials) {
		this.encodedCredentials = encodedCredentials;
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
	
}
