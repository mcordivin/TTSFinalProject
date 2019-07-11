package com.mc.TTSFinalProject;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.mc.TTSFinalProject.Model.Item;
import com.mc.TTSFinalProject.Repository.ItemRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TTSFinalProjectRepositoryUnitTest {
	private static Item setUpDomains;
	private static Item createProject;
	
	@Autowired
	ItemRepository itemRepository;
	
	@Autowired
	TestEntityManager testEntityManager;
	
	@Before
	public void setUp() {
		setUpDomains = new Item("Establish domains", "Brainstorm domains and properties", "DevCoach", true);
		createProject = new Item("Create Spring Project", "Create project with correct group/artifact id and dependencies", "DevCoach", false);
		testEntityManager.persistAndFlush(setUpDomains);
		testEntityManager.persistAndFlush(createProject);
		}
	
	@After
	public void tearDown() {
		itemRepository.deleteAll();
	}
	
	@Test
	public void whenGivenAll_ReturnAllList() {
		List<Item> found = itemRepository.findAll();
		assertThat(2).isEqualTo(found.size());
	}
	
	@Test
	public void whenGivenStatus_ReturnItemListByStatus() {
		List<Item> found = itemRepository.findAllByStatus(setUpDomains.getStatus());
		assertThat(1).isEqualTo(found.size());
	}
	
//	@Test
//	public void whenGivenUpdate_ReturnUpdatedItem() {
//		Item addCss = new Item("Add CSS", "Add CSS for styling", "DevCoach", false);
//		Item updatedAddCss = new Item("Add CSS", "Add CSS for styling", "DevCoach", true);
//		Item found = itemRepository.updateByStatus(addCss, false);
//		assertThat(updatedAddCss).isEqualTo(found);
//	}
//	
//	@Test
//	public void whenDeleteAllByStatus_ReturnUpdated() {
//		itemRepository.deleteByStatus(item, Boolean status);
//	}

}


//public void deleteByStatus(Post post, Boolean status);




