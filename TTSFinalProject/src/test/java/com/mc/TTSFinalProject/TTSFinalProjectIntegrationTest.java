package com.mc.TTSFinalProject;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.mc.TTSFinalProject.Model.Item;
import com.mc.TTSFinalProject.Repository.ItemRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(
		webEnvironment=WebEnvironment.MOCK,
		classes = TtsFinalProjectApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
		locations="classpath:application-integrationtest.properties")

public class TTSFinalProjectIntegrationTest {
	@Autowired
	MockMvc mvc;
	
	@Autowired
	ItemRepository itemRepository;
	
	@Before
	public void setUp() {
		Item dontQuit = new Item ("Don't quit", "I know this is frustrating but hang in there!", "Me", true);
		Item createPrimaryKey = new Item("Create Primary Key", "Create DB Primary key", "DevCoach", false);
		itemRepository.save(dontQuit);
		itemRepository.save(createPrimaryKey);
	}
	
	@After
	public void tearDown() {
		itemRepository.deleteAll();
	}
	
	@Test
	public void givenItem_whenGetHome_thenStatus200() throws Exception {
		mvc.perform(get("/api/all")
				.contentType(MediaType.TEXT_HTML))
				.andExpect(status().isOk())
				.andExpect(content()
				.contentTypeCompatibleWith(MediaType.TEXT_HTML));
				//.andExpect(jsonPath("$[0].title", is("Don't quit")));
	}

}
