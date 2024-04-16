package com.geomhwein.go.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.geomhwein.go.command.HomeworkVO;
import com.geomhwein.go.creator.service.CreatorService;




@Controller
@RequestMapping("/creator")
public class CreatorController {
	
	@Autowired
	@Qualifier("creatorService")
	private CreatorService creatorService;
	

	@GetMapping("/questionList")
	public String questionList() {
		
		return "creator/questionList";
	}
	
	
	@PostMapping("/registHomeworkForm")
	public String registHomeworkForm(HomeworkVO vo) {
		int result=creatorService.makeHomework(vo);
		if(result==1) {
			return "/";
		}else {
			return "creator/creatorFail";
		}
		
		
	}

	
	
}
