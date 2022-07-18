package com.rewards.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.rewards.backend.repository.ItemRepository;

@RestController
public class ItemController {
	@Autowired
	private ItemRepository itemRepository;
	
	
}
