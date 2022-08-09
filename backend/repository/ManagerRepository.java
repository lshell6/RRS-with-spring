package com.rewards.backend.repository;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.rewards.backend.model.Manager;

public interface ManagerRepository extends JpaRepository<Manager, Long>{
	@Query("select m from manager m where m.username=?1")
	Manager getByManagerUsername(String username);
	
	@Transactional
	@Modifying
	@Query("update manager m SET m.password=?2,m.passwordLastReset=?3 where m.username=?1")
	void resetPassword(String username, String password,LocalDate date);
}
