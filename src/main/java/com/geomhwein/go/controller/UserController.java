package com.geomhwein.go.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequestMapping("/user")
public class UserController {
	
	@GetMapping("/comunityList")
	public String userComunityList() {
		return "user/ComunityList";
	}
	
	@GetMapping("/comunityDetail")
	public String questionDetail() {
		return "user/comunityDetail";
	}
	
	@GetMapping("/comunityReg")
	public String comunityReg() {
		return "user/comunityReg";
	}
	
	@GetMapping("/comunityModify")
	public String comunityModify() {
		return "user/comunityModify";
	}
	
	@GetMapping("/groupList")
	public String userGroupList() {
		return "user/GroupList";
	}
	
	@GetMapping("/questionList")
	public String questionList() {
		return "user/questionList";
	}
	
	@GetMapping("/homeworkReg")
	public String homeworkReg() {
		return "user/homeworkReg";
	}
	
	@GetMapping("/homeworkList")
	public String homeworkList() {
		return "user/homeworkList";
	}
	@GetMapping("/makeQuestion")
	public String makeQuestion(@RequestParam("username")String username,Model model){
		model.addAttribute("username",username);
		return "user/makeQuestion";
	}
	@PostMapping("/registQuestionForm")
	public String registQuestionForm(@RequestParam("userId")String userId,@RequestParam("questionDate")String questionDate,@RequestParam("questionCn")String questionCn) {
		
		
		return "";
	}
	



}
