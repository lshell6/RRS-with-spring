package com.rewards.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "transaction")
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable = false, columnDefinition = "integer default 0")
	private Integer num_of_items;
	@Column(nullable = false, columnDefinition = "integer default 0")
	private Integer point_value;
	@Column(nullable = false)
	private String employee_username;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getNum_of_items() {
		return num_of_items;
	}
	public void setNum_of_items(Integer num_of_items) {
		this.num_of_items = num_of_items;
	}
	public Integer getPoint_value() {
		return point_value;
	}
	public void setPoint_value(Integer point_value) {
		this.point_value = point_value;
	}
	public String getEmployee_username() {
		return employee_username;
	}
	public void setEmployee_username(String employee_username) {
		this.employee_username = employee_username;
	}
	public Transaction(Long id, Integer num_of_items, Integer point_value, String employee_username) {
		super();
		this.id = id;
		this.num_of_items = num_of_items;
		this.point_value = point_value;
		this.employee_username = employee_username;
	}
	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "transaction [id=" + id + ", num_of_items=" + num_of_items + ", point_value=" + point_value
				+ ", employee_username=" + employee_username + "]";
	}
	
	
	
}
