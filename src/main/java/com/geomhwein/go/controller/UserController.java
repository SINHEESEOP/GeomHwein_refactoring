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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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


	public String userComunityList(Model model) {
		
		
		List<comunityVO> list = userService.getComunityList();
		
		model.addAttribute("list" , list);
		
		return "user/comunityList";
	}
	
	@GetMapping("/comunityDetail")
	public String comunityDetail(@RequestParam("pst_ttl_no") int pst_ttl_no , Model model) {
		
	
		
		comunityVO vo = userService.getComunityDetail(pst_ttl_no);
		
		model.addAttribute("vo",vo);
		
		return "user/comunityDetail";
	}
	
	@GetMapping("/comunityReg")
	public String comunityReg() {
		return "user/comunityReg";
	}
	
	@GetMapping("/comunityModify")
	public String comunityModify(@RequestParam("pst_ttl_no") int pst_ttl_no , Model model) {
		
		comunityVO vo = userService.getComunityDetail(pst_ttl_no);
		
		model.addAttribute("vo",vo);
		
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
	public String comunityForm(comunityVO vo , RedirectAttributes rec) {
		
		int result = userService.comunityForm(vo);
		
		if(result == 1 ) {
			rec.addFlashAttribute("msg", "성공입니다");
		}else {
			rec.addFlashAttribute("msg", "실패했습니다");
		}
		
		return "redirect:/user/comunityList";
	}
	
	@PostMapping("/comunityModifyForm")
	public String comunityModifyForm(comunityVO vo , RedirectAttributes rec) {
		
		int result = userService.comunityModifyForm(vo);
		
		if(result == 1 ) {
			rec.addFlashAttribute("msg", "성공입니다");
		}else {
			rec.addFlashAttribute("msg", "실패했습니다");
		}
		
		return "redirect:/user/comunityList";
	}
	


}
