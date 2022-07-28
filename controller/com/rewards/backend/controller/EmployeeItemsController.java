package com.rewards.backend.controller;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rewards.backend.model.Employee;
import com.rewards.backend.model.EmployeeItems;
import com.rewards.backend.model.Item;
import com.rewards.backend.repository.EmployeeItemsRepository;
import com.rewards.backend.repository.EmployeeRepository;
import com.rewards.backend.repository.ItemRepository;

@RestController
public class EmployeeItemsController {
	@Autowired
	private EmployeeRepository empRepository;
	@Autowired
	private ItemRepository itemRepository;
	@Autowired
	private EmployeeItemsRepository empItemsRepository;
	
	@PostMapping("/employee/item/{eid}/{iId}")
	public EmployeeItems purchaseAPI(@PathVariable("eid") Long eid, @PathVariable("iId") Long iId, @RequestBody EmployeeItems ei) {
		Optional<Employee> optionalE = empRepository.findById(eid);
		Optional<Item> optionalI = itemRepository.findById(iId);
		if(!optionalE.isPresent())
			throw new RuntimeException("invalid employee id given");
		if(!optionalI.isPresent())
			throw new RuntimeException("invalid item id given");
		Employee e = optionalE.get();
		Item i = optionalI.get();
		ei.setEmployee(e);
		ei.setItem(i);
		ei.setQuantity(0);
		return empItemsRepository.save(ei);
	}
	@GetMapping("/item/employee/{eid}")
	public List<String> getItemsByEmployeeId(@PathVariable("eid") Long eid){
		Optional<Employee> optional = empRepository.findById(eid);
		if(!optional.isPresent())
			throw new RuntimeException("Employee ID invalid");
		Employee e = optional.get();
		List<String> list = empItemsRepository.getItemsByEmployeeId(e.getId());
		return list;
	}
	@GetMapping("/employee/item/{iId}")
	public List<String> getEmployeesByItemId(@PathVariable("iId") Long iId){
		Optional<Item> optional = itemRepository.findById(iId);
		if(!optional.isPresent())
			throw new RuntimeException("Item ID invalid");
		Item i = optional.get();
		List<String> list = empItemsRepository.getEmployeesByItemId(i.getId());
		return list;
	}
}
