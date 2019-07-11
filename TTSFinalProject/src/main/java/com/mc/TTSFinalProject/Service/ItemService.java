package com.mc.TTSFinalProject.Service;

import java.util.List;

import com.mc.TTSFinalProject.Model.Item;


public interface ItemService {
	public List<Item> findAll();
	public List<Item> findByStatus(Boolean status);
	public Item saveEntity(Item item);
	public void updateEntityById(Long id, Item item);
	public void deleteAll();
	public void deleteByStatus(Boolean status);
	public void deleteById(Long id);
}
