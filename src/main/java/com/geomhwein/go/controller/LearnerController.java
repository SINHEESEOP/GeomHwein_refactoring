package com.geomhwein.go.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/learner")
@Controller
public class LearnerController {

	@GetMapping("/profile")
	public String profile() {
		return "learner/profile";
	}

	@GetMapping("/billing")
	public String billing() {
		return "learner/billing";
	}

}
