package com.rewards.backend.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.rewards.backend.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long>{

}
