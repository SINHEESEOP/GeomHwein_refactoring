package com.geomhwein.go.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.geomhwein.go.command.HomeworkVO;
import com.geomhwein.go.command.educationGroupVO;
import com.geomhwein.go.creator.service.CreatorService;




@Controller
@RequestMapping("/creator")
public class CreatorController {
	
	@Autowired
	@Qualifier("creatorService")
	private CreatorService creatorService;
	
	
	@GetMapping("/eduGroup")
	public String eduGroup  () {
		
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
	@GetMapping("/groupApplyList")
	public String groupApplyList(Model model) {
		int applyCount=creatorService.getApplyCount();
		if(applyCount==0) {
			return "creator/cretorFail";
		}
		List<educationGroupVO> applyList= new ArrayList<>();
		for(int i=1;i<=applyCount;i++) {
			applyList.add(creatorService.getApply(i));
		}
		model.addAttribute("applyList",applyList);
		return "creator/groupApplyList";
	}
	
	
}
