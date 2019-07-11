package com.mc.TTSFinalProject.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mc.TTSFinalProject.Model.Item;

public interface ItemRepository extends JpaRepository <Item, Long> {
	public List<Item> findAllByStatus(Boolean status);
	public void deleteByStatus(Boolean status);
	public void deleteItemById(Long id);
	public Item findItemById(Long id);
}
