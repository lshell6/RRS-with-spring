package com.rewards.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rewards.backend.model.Manager;

public interface ManagerRepository extends JpaRepository<Manager, Long>{
  @Query("select m from Manager m where m.username=?1")
  Manager getByUsername(String username);


@Transactional
@Modifying
@Query("update Manager m SET m.name=?2,m.securityQuestion=?3,m.securityAnswer=?4 "
+ " where m.username=?1")
void updateProfile(String username, String name, String securityQuestion, 
String securityAnswer);


@Transactional
@Modifying
@Query("update Manager m SET m.password=?2 where m.username=?1")
void resetPassword(String username, String password, LocalDate date);
}
