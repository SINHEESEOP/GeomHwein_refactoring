package com.geomhwein.go.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.geomhwein.go.command.HomeworkVO;
import com.geomhwein.go.creator.service.CreatorService;




@Controller
@RequestMapping("/creator")
public class CreatorController {
	
	@Autowired
	@Qualifier("creatorService")
	private CreatorService creatorService;
	
	
	@GetMapping("/eduGroup")
	public String eduGroup  (
		//시큐리티사용시 적용 현재는 비활성화	//@RequestParam("username")String username,Model model
							) {
		//model.addAttribute("username",username );
		return "creator/eduGroup";
	}
	

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
