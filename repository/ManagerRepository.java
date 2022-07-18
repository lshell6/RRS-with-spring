package com.rewards.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rewards.backend.model.Manager;

public interface ManagerRepository extends JpaRepository<Manager, Long>{

}
