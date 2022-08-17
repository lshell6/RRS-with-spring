package com.rewards.backend.dto;


public class ManagerEditDto {

private Long id;

private String name;

private String securityAnswer;

private String securityQuestion;

public ManagerEditDto() {

super();

// TODO Auto-generated constructor stub

}

public ManagerEditDto(Long id, String name, String securityAnswer, String securityQuestion) {

super();

this.id = id;

this.name = name;

this.securityAnswer = securityAnswer;

this.securityQuestion = securityQuestion;

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

public String getSecurityAnswer() {

return securityAnswer;

}

public void setSecurityAnswer(String securityAnswer) {

this.securityAnswer = securityAnswer;

}

public String getSecurityQuestion() {

return securityQuestion;

}

public void setSecurityQuestion(String securityQuestion) {

this.securityQuestion = securityQuestion;

}


}


