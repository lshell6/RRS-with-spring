package com.rewards.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rewards.backend.model.Employee;
import com.rewards.backend.model.GivePoints;
import com.rewards.backend.model.Manager;

public interface GivePointsRepository extends JpaRepository<GivePoints, Long>{

	@Query("select gp.employee from GivePoints gp where gp.manager.id=?1")
	List<Employee> getEmployeeByManagerId(Long mid);

	@Query("select gp.manager from GivePoints gp where gp.employee.id=?1")
	List<Manager> getManagerByEmployeeId(Long eid);

}
