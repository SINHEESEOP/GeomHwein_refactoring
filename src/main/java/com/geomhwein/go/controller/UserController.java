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
import com.geomhwein.go.command.educationGroupVO;
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
		
		//숙제조회 클릭시 다시 화면으로 값 model에 담아서 List<HomeworkVO> 로 보냄
		//타임리프 반복문 돌려서 화면에서 띄워주면됨
	}
	@GetMapping("homeworkList")
	public String homeworkList() {
		return "user/homeworkList";
	}

	@GetMapping("/makeQuestion")
	public String makeQuestion(@RequestParam("username")String username,Model model){
		model.addAttribute("username",username);
		//질문등록하기 팝업으로 보냄 ,username을 모델에 담아서 화면에서 readonly로 뿌려줌
		return "user/makeQuestion";
	}
	@PostMapping("/registQuestionForm")
	public void registQuestionForm(@RequestParam("userId")String userId,@RequestParam("questionDate")String questionDate,@RequestParam("questionCn")String questionCn,QuestionVO vo) {
		vo.setUserId(userId);
		vo.setQusCn(questionCn);
		vo.setQusYmd(questionDate);
		userService.addQuestion(vo);
		//질문내용을 DB에 저장하는 메서드
	}
	
	@PostMapping("/creatorRegForm")
	public String creatorRegForm(@RequestParam("userName")String userName,@RequestParam("docsCode")String docsCode,@RequestParam("reason")String reason) {
		userService.registCreator(userName,docsCode,reason);
		//교육자 신청 되는 기록 신청가능여부를 위해 DB에 넣어서
		//관리자 창 열릴때 get방식으로 불러와서 값 보내주면됨
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
	@GetMapping("/groupSelectForm")
	public String groupSelectForm(  @RequestParam("groupNm")String groupNm,
									@RequestParam("userId")String userId,
									@RequestParam("groupUtztnNope")int groupUtztnNope,
									@RequestParam("lastCmcrsYmd")String lastCmcrsYmd,
									@RequestParam("groupCost")int groupCost,
									@RequestParam("recAge")int recAge,
									educationGroupVO vo,
									Model model) {
		vo.setGroupCost(groupCost);
		vo.setGroupNm(groupNm);
		vo.setGroupUtztnNope(groupUtztnNope);
		vo.setLastCmcrsYmd(lastCmcrsYmd);
		vo.setRecAge(recAge);
		vo.setUserId(userId);
		model.addAttribute(vo);
		
		return "user/??";//그룹신청하는폼 또는 화면
	}


}
