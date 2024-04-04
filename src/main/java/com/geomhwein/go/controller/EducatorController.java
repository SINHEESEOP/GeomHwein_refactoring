package com.geomhwein.go.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/educator")
@Controller
public class EducatorController {

	@GetMapping("/profile")
	public String profile() {
		return "educator/profile";
	}

}
