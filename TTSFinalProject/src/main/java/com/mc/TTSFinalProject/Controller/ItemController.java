package com.mc.TTSFinalProject.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.mc.TTSFinalProject.Service.ItemServiceImpl;

@Controller
public class ItemController {

	@Autowired
	ItemServiceImpl itemService;
	
	@GetMapping(value="/")
	public String home() {
		return "index";
	}
}
