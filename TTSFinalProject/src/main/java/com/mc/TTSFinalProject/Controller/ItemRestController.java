package com.mc.TTSFinalProject.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mc.TTSFinalProject.Model.Item;
import com.mc.TTSFinalProject.Service.ItemServiceImpl;

@RestController
@RequestMapping(value="/api")
public class ItemRestController {

	@Autowired
	ItemServiceImpl itemService;
	
	@GetMapping(value="/all")
	public List<Item> findAll(){
		return itemService.findAll();
	}
	
	@GetMapping(value="/byStatus/{status}")
	public List<Item> findByStatus(@PathVariable Boolean status) {
		return itemService.findByStatus(status);
	}
	
	@PostMapping(value="/new")
	public Item createNew(Item item) {
		itemService.saveEntity(item);
		return item;
	}
	
	@PutMapping(value="/updateByStatus")
	public Item updateItemById(Long id, Item item) {
		itemService.updateEntityById(id, item);
		return item;
	}
	
	@DeleteMapping(value="/deleteAll")
	public String deleteAll() {
		itemService.deleteAll();
		return "<h3>Successful delete!</h3>";
	}
	
	@DeleteMapping(value="/deleteByStatus")
	public String deleteByStatus(Boolean status) {
		itemService.deleteByStatus(status);
		return "<h3>Completed items deleted!</h3>";
	}
	
	@DeleteMapping("/items/{id}")
	public String deleteById(@PathVariable Long id){
		itemService.deleteById(id);
		return "<h3>Delete student successful</h3>";
	}
}
