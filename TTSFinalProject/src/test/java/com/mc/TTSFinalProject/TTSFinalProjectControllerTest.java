package com.mc.TTSFinalProject;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.mc.TTSFinalProject.Controller.ItemRestController;
import com.mc.TTSFinalProject.Model.Item;
import com.mc.TTSFinalProject.Service.ItemServiceImpl;

@RunWith(SpringRunner.class)
@WebMvcTest(ItemRestController.class)
public class TTSFinalProjectControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	ItemServiceImpl itemService;
	
	@Test
	public void givenAllItems_thenReturnJsonArray () throws Exception {
		Item createHomePage = new Item ("Create Homepage", "Create an HTML file for your homepage", "DevCoach", true);
	
		List<Item> allItems = Arrays.asList(createHomePage);
		
		given(itemService.findAll()).willReturn(allItems);
		
		mvc.perform(get("/api/all")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].title", is(createHomePage.getTitle())));
	}

}
