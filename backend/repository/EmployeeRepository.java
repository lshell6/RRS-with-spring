package com.rewards.backend.repository;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.rewards.backend.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	@Query("select e from employee e where e.username=?1")
	Employee getByEmployeeUsername(String username);
	
	@Transactional
	@Modifying
	@Query("update Employee e SET e.password=?2,e.passwordLastReset=?3 where e.username=?1")
	void resetPassword(String username, String password, LocalDate date);
}
