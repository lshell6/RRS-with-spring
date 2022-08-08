package com.rewards.backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class GivePoints {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private int points;
	
	@OneToOne
	private Manager manager;
	
	@OneToOne
	private Employee employee;

	public GivePoints() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GivePoints(Long id, int points, Manager manager, Employee employee) {
		super();
		this.id = id;
		this.points = points;
		this.manager = manager;
		this.employee = employee;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Override
	public String toString() {
		return "GivePoints [id=" + id + ", points=" + points + ", manager=" + manager + ", employee=" + employee + "]";
	}
	
	
}
