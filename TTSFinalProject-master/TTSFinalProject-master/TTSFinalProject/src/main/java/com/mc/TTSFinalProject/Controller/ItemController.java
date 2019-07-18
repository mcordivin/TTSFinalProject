package com.mc.TTSFinalProject.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.mc.TTSFinalProject.Model.Item;
import com.mc.TTSFinalProject.Service.ItemServiceImpl;

@Controller
public class ItemController {

	@Autowired
	ItemServiceImpl itemService;
	
	@GetMapping(value="/")
	public String home() {
		return "index";
	}
	
	@GetMapping(value="/allItems")
	public String findAll(Model model) {
		List<Item> allItems = itemService.findAll();
		model.addAttribute("allItems", allItems);
		return "items";
	}
	
	@GetMapping(value="/item/{id}")
	public String itemById(@PathVariable Long id, Model model) {
		Item item = itemService.findItemById(id);
		model.addAttribute("item", item);
		return "item";
	}
	
	@GetMapping(value="/items/{status}")
	public String completedItems (@PathVariable Boolean status, Model model) {
		List<Item> statusItem = itemService.findByStatus(status);
		model.addAttribute("statusItem", statusItem);
		return "statusItems";	
	}
	
	@GetMapping(value="/new")
	public String newItem(Item newItem) {
		return "newItem";
	}
	
	@PostMapping(value="/new")
	public String createNew(Model model, Item newItem) {
		itemService.saveEntity(newItem);
		model.addAttribute("message", "ToDo Item saved!");
		return "result";
	}
	
	@DeleteMapping(value="/deleteAll")
	public String deleteAll(Model model) {
		itemService.deleteAll();
		model.addAttribute("message", "All items deleted!");
		return "result";
	}
	
//	@DeleteMapping(value="/item/{id}")
//	public String deleteItemById(@PathVariable Long id, Model model) {
//		itemService.deleteById(id);
//		model.addAttribute("message", "Item deleted Successfully");
//		return "result";
//	}
	
	@DeleteMapping(value="/deleteItemById")
	public String deleteItemById(Long id, Model model) {
		itemService.deleteById(id);
		model.addAttribute("message", "Item deleted!");
		return "result";
	}
	
	@DeleteMapping(value="/deleteByStatus")
	public String deleteOne(Boolean status, Model model) {
		itemService.deleteByStatus(status);
		model.addAttribute("message", "Items deleted!");
		return "result";
	}	
	
	@GetMapping(value="/update/{id}")
	public String updateItem(@PathVariable Long id, Item item, Model model) {
		Item currentItem = itemService.findItemById(id);
		model.addAttribute("currentItem", currentItem);
		return "update";
	}
	
	@PutMapping(value="/update/{id}")
	public String updateItemById(@PathVariable Long id, Item item, Model model) {
		itemService.updateEntityById(id, item);
		model.addAttribute("message", "Item updated!");
		return "result";
	}

}
