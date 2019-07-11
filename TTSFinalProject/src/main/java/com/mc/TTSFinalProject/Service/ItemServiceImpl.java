package com.mc.TTSFinalProject.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mc.TTSFinalProject.Model.Item;
import com.mc.TTSFinalProject.Repository.ItemRepository;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	ItemRepository itemRepository;

	@Override
	public List<Item> findAll() {
		return itemRepository.findAll();
	}

	@Override
	public List<Item> findByStatus(Boolean status) {
		return itemRepository.findAllByStatus(status);
	}

	@Override
	public Item saveEntity(Item item) {
		itemRepository.save(item);
		return item;
	}

	@Override
	public void updateEntityById(Long id, Item item) {
		Item itemInDB = itemRepository.findItemById(id);
		String dbTitle = itemInDB.getTitle();
		String dbDescription = itemInDB.getDescription();
		String dbCreator = itemInDB.getCreator();
		Boolean dbStatus = itemInDB.getStatus();
		if (dbTitle != null) itemInDB.setTitle(dbTitle);
		if (dbDescription != null) itemInDB.setDescription(dbDescription);
		if (dbCreator != null) itemInDB.setCreator(dbCreator);
		if (dbStatus != null) itemInDB.setStatus(dbStatus);
		itemRepository.save(itemInDB);
	}

	@Override
	public void deleteAll() {
		itemRepository.deleteAll();
	}

	@Override
	public void deleteByStatus(Boolean status) {
		itemRepository.deleteByStatus(status);
	}

	@Override
	public void deleteById(Long id) {
		itemRepository.deleteItemById(id);
	}

}