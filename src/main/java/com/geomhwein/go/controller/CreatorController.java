package com.geomhwein.go.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/creator")
public class CreatorController {
	
	@GetMapping("/eduGroup")
	public String eduGroup() {
		return "creator/eduGroup";
	}
	
	
	
}
