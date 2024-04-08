package com.geomhwein.go.controller;

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
import com.geomhwein.go.command.QuestionVO;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


import com.geomhwein.go.command.comunityVO;
import com.geomhwein.go.user.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@GetMapping("/cart")
	public String cart() {
		return "/user/cart";
	}

	@GetMapping("/billing")
	public String billing() {
		return "/user/billing";
	}

	@GetMapping("/profile")
	public String profile() {
		return "/user/profile";
	}
	
	@Autowired
	@Qualifier("userService")
	private UserService userService;
	

	@GetMapping("/comunityList")
	public String userComunityList() {
		return "user/comunityList";
	}
	
	@GetMapping("/comunityDetail")
	public String comunityDetail() {
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
		return "user/groupList";
	}
	
	@GetMapping("/groupApplyList")
	public String groupApplyList() {
		return "user/groupApplyList";
	}
	
	@GetMapping("/questionList")
	public String questionList() {
		return "user/questionList";
	}
	
	@GetMapping("/homeworkReg")
	public String homeworkReg() {
		return "user/homeworkReg";
	}
	
	@GetMapping("/viewHomework")
	public String homeworkList(Model model,@RequestParam("userId")String userId) {
		List<HomeworkVO> list=userService.getHomeworkList(userId);
		model.addAttribute("homeworkList",list);
		return "user/HomeworkReg";
	}
	@GetMapping("homeworkList")
	public String homeworkList() {
		return "user/homeworkList";
	}

	@GetMapping("/makeQuestion")
	public String makeQuestion(@RequestParam("username")String username,Model model){
		model.addAttribute("username",username);
		return "user/makeQuestion";
	}
	@PostMapping("/registQuestionForm")
	public void registQuestionForm(@RequestParam("userId")String userId,@RequestParam("questionDate")String questionDate,@RequestParam("questionCn")String questionCn,QuestionVO vo) {
		vo.setUserId(userId);
		vo.setQusCn(questionCn);
		vo.setQusYmd(questionDate);
		userService.addQuestion(vo);
	}
	
	@PostMapping("/creatorRegForm")
	public String creatorRegForm(@RequestParam("userName")String userName,@RequestParam("docsCode")String docsCode,@RequestParam("reason")String reason) {
		userService.registCreator(userName,docsCode,reason);
		
		return "user/profile";//신청버튼 잇던 곳으로 보내주면됨
	}

	@GetMapping("/questionReg")
	public String questionReg() {
		return "user/questionReg";
	}
	
	@GetMapping("/questionDetail")
	public String questionDetail() {
		return "user/questionDetail";
	}
	
	@GetMapping("/questionModify")
	public String questionModify() {
		return "user/questionModify";
	}
	
	@PostMapping("/comunityForm")
	public String comunityForm(comunityVO vo) {
		
		
		return "redirect:/user/comunityList";
	}
	


}
