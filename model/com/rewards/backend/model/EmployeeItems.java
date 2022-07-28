package com.rewards.backend.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="employee_has_items")
public class EmployeeItems {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private int quantity;
	@OneToOne
	private Employee employee;
	@OneToOne
	private Item item;
	public EmployeeItems() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EmployeeItems(Long id, int quantity, Employee employee, Item item) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.employee = employee;
		this.item = item;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	@Override
	public String toString() {
		return "EmployeeItems [id=" + id + ", quantity=" + quantity + ", employee=" + employee + ", item=" + item + "]";
	}
	
}
