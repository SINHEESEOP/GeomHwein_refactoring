package com.geomhwein.go.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.geomhwein.go.command.HomeworkVO;
import com.geomhwein.go.command.QuestionVO;
import com.geomhwein.go.command.EducationGroupVO;
import com.geomhwein.go.creator.service.CreatorService;
import com.geomhwein.go.securlty.UserAuth;




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
	@GetMapping("/homeworkDetail")
	public String homeworkDetail(@RequestParam("userId")String userId,
								 @RequestParam("asmtNo")int asmtNo,
								 @RequestParam("asmtNm")String asmtNm,
								 @RequestParam("asmtAns")String asmtAns,
								 Model model,
								 HomeworkVO vo) {
		vo.setAsmtAns(asmtAns);
		vo.setAsmtNo(asmtNo);
		vo.setUserId(userId);
		vo.setAsmtNm(asmtNm);
		model.addAttribute("homeworkDetail",vo);
		
		
		return "creator/homeworkDetail";
	}
	@GetMapping("/makeCorrect")
	public String makeCorrect(@RequestParam("userId")String userId,
							  @RequestParam("asmtScr")int asmtScr,
							  @RequestParam("asmtAnsNo")int asmtAnsNo,
							  Model model) {
		int currentScore=creatorService.getUserScore(userId);
		int newScore=currentScore+asmtScr;
		creatorService.setUserScore(userId,newScore);			 
		creatorService.deleteAns(asmtAnsNo);
		return "creator/getHomeworkDoneList";
	}
	
	
	

	@GetMapping("/questionList")
	public String questionList(Model model) {
		List<QuestionVO> qList= new ArrayList<>();
		return "creator/questionList";
	}
	
	@GetMapping("/createHomework")
	public String createHomework(Authentication authentication,Model model) {
		if (authentication != null) {
			UserAuth userAuth = (UserAuth)authentication.getPrincipal();

			String userId  = userAuth.getUsername();
			model.addAttribute("userId",userId);
			return "creator/makeHomework";
		}else {
			return "creator/noAuth";
		}
		
		
	}
	@GetMapping("/getHomeworkDoneList")
	public String getHomeworkDoneList(Model model,Authentication authentication) {
		if (authentication != null) {
			UserAuth userAuth = (UserAuth)authentication.getPrincipal();

			String userId  = userAuth.getUsername();//선생님 ID
			List<HomeworkVO> homeworkDoneList=creatorService.getHomeworkDone(userId);
			model.addAttribute("hwdList",homeworkDoneList);
			
			
			return "creator/homeworkList";
			
		}else {
			return "redirect:/";
		}
			
		
		
	}
	
	
	@PostMapping("/registHomeworkForm")
	public void registHomeworkForm(HomeworkVO vo) {
		creatorService.makeHomework(vo);
		
			
		
		
		
	}
	@GetMapping("/groupApplyList")
	public String groupApplyList(Model model) {
		int applyCount=creatorService.getApplyCount();
		if(applyCount==0) {
			return "redirect:/";
		}
		List<EducationGroupVO> applyList= new ArrayList<>();
		for(int i=1;i<=applyCount;i++) {
			applyList.add(creatorService.getApply(i));
		}
		model.addAttribute("applyList",applyList);
		return "creator/groupApplyList";
	}
	
	
}
