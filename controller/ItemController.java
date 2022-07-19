package com.rewards.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rewards.backend.model.Item;
import com.rewards.backend.repository.ItemRepository;

@RestController
public class ItemController {
	@Autowired
	private ItemRepository itemRepository;
	
	@PostMapping("/item")
	public void postItem(@RequestBody Item item) {
		itemRepository.save(item);
	}
	
	@GetMapping("/item/single/{id}")
	public Item getSingleItemById(@PathVariable("id") Long id) {
		Optional<Item> optional = itemRepository.findById(id);
		if(optional.isPresent())
			return optional.get();
		throw new RuntimeException("ID is invalid");
	}
	
	@DeleteMapping("/item/{id}")
	public void deleteItem(@PathVariable("id") Long id) {
		itemRepository.deleteById(id);
	}
	
	@PutMapping("/item/{id}")
	public Item updateItem(@PathVariable("id") Long id,
			@RequestBody Item newItem){
		Optional<Item> optional = itemRepository.findById(id);
		if(optional.isPresent()) {
			Item existingItem = optional.get();
			existingItem.setId(newItem.getId());
			existingItem.setName(newItem.getName());
			existingItem.setPoint_value(newItem.getPoint_value());
			return itemRepository.save(existingItem);
		}else
			throw new RuntimeException("ID is invalid");
	}
	
	@GetMapping("/item")
	public List<Item> getAllItems(){
		List<Item> list = itemRepository.findAll();
		return list;
	}
}
