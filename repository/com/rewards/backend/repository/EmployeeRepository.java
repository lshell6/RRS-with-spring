package com.rewards.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rewards.backend.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
