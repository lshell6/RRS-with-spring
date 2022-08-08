package com.rewards.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="items")
public class Item {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Column(nullable=false)
	private String name;
	@Column(columnDefinition = "integer default 0")
	private Integer point_value;
	public Item() {
		super();
	}
	public Item(Long id, String name, Integer point_value) {
		super();
		this.id = id;
		this.name = name;
		this.point_value = point_value;
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
	public Integer getPoint_value() {
		return point_value;
	}
	public void setPoint_value(Integer point_value) {
		this.point_value = point_value;
	}
	@Override
	public String toString() {
		return "item [id=" + id + ", name=" + name + ", point_value=" + point_value + "]";
	}
	
	
}
