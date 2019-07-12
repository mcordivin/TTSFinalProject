package com.mc.TTSFinalProject;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.mc.TTSFinalProject.Model.Item;
import com.mc.TTSFinalProject.Repository.ItemRepository;
import com.mc.TTSFinalProject.Service.ItemService;
import com.mc.TTSFinalProject.Service.ItemServiceImpl;

@RunWith(SpringRunner.class)
public class TTSFinalProjectServiceUnitTest {
	private static List<Item> itemList;
	private static List<Item> completedList;
	private static List<Item> toDoList;
	private static Item createRepoTest;
	private static Item createRepository;
	
	@TestConfiguration
	static class ItemServiceTestContextConfiguration {
		@Bean
		public ItemService itemService() {
			return new ItemServiceImpl();
		}
	}
	
	@Autowired
	private ItemService itemService;
	
	@MockBean
	ItemRepository itemRepository;
	
	@Before
	public void setUp() {
		Item createRepoTest = new Item ("Create a test for repository items", 
				"In TDD, you need to create the test before the code",
				"DevCoach", true); 
		Item createRepository = new Item ("Create repository", 
				"Create an interface for Repository; extend JpaDataRepository",
				"DevCoach", false); 
		List<Item> itemList = new ArrayList<>();
		itemList.add(createRepoTest);
		itemList.add(createRepository);
		
		List<Item> completedList = new ArrayList<>();
		completedList.add(createRepoTest);
		
		
		List<Item> toDoList = new ArrayList<>();
		toDoList.add(createRepository);
	}
	
	
	@Test
	public void whenGivenItems_ReturnAll() {
		Mockito.when(itemRepository.findAll()).thenReturn(itemList);
		List <Item> found = itemService.findAll();
		assertEquals(itemList, found);
	}
	
	@Test
	public void whenGivenTrueStatus_ReturnTrueStatusItems() {
		Mockito.when(itemRepository.findAllByStatus(true)).thenReturn(completedList);
		List <Item> found = itemService.findByStatus(true);
		assertEquals(completedList, found);
	}
	
	@Test
	public void whenGivenFalseStatus_ReturnFalseStatusItems() {
		Mockito.when(itemRepository.findAllByStatus(false)).thenReturn(toDoList);
		List <Item> found = itemService.findByStatus(false);
		assertEquals(toDoList, found);
	}
	
//	@Test
//	public void whenGivenNewItem_ReturnSuccessMessage() {
//		Mockito.when(itemRepository.save(createRepoTest)).thenReturn(createRepoTest);
//		Item found = itemService.saveEntity(createRepoTest);
//		assertEquals(createRepoTest, found);
//	}
}
