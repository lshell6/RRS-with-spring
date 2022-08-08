package com.rewards.backend.dto;

public class ManagerDto {
	private String name;
	private String securityQuestion;
	private String securityAnswer;
	private int points_given;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public int getPointsGiven() {
		return points_given;
	}
	public void setPointsGiven(int pointsGiven) {
		this.points_given = pointsGiven;
	}
	
}
