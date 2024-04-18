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
import com.geomhwein.go.command.SubmissionVO;
import com.geomhwein.go.command.UserDetailsVO;
import com.geomhwein.go.command.EducationGroupVO;
import com.geomhwein.go.creator.service.CreatorService;
import com.geomhwein.go.securlty.UserAuth;
import org.springframework.web.bind.annotation.RequestBody;





@Controller
@RequestMapping("/creator")
public class CreatorController {
	
	@Autowired
	@Qualifier("creatorService")
	private CreatorService creatorService;
	
	@GetMapping("/creatorMain")
	public String creatorMain(Model model) {
		List<UserDetailsVO> sList=creatorService.getAllStudent();
		model.addAttribute("studentList",sList);
		
		return "creator/creatorMain";
	}
	
	
	
	@GetMapping("/eduGroup")
	public String eduGroup  () {
		
		return "creator/eduGroup";
	}
	
	
	
	

	@GetMapping("/viewQuestionList")
	public String questionList(Model model,Authentication authentication) {
		if (authentication != null) {
			UserAuth userAuth = (UserAuth)authentication.getPrincipal();

			String userId  = userAuth.getUserId();
			List<QuestionVO> qList= creatorService.getQuestionList(userId);
			model.addAttribute("questionList",qList);
			return "creator/questionList";
		}else {
			return "redirect:/";	
			
		}
	}
	
	@GetMapping("/createHomework")
	public String createHomework(Authentication authentication,Model model) {
		if (authentication != null) {
			UserAuth userAuth = (UserAuth)authentication.getPrincipal();

			String userId  = userAuth.getUserId();
			System.out.println(userId);
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

			String userId  = userAuth.getUserId();//선생님 ID
			System.out.println(userId);
			List<SubmissionVO> homeworkDoneList=creatorService.getHomeworkDone(userId);
			  
			
			model.addAttribute("subList",homeworkDoneList);
			 
			
			
			return "creator/homeworkList";
			
		}else {
			return "redirect:/";
		}
			
		
		
	}
	
	@GetMapping("/homeworkDetail")  //상세보기
	public String homeworkDetail(@RequestParam("subNo")String sNo,Model model) {
		int subNo=Integer.parseInt(sNo);
		SubmissionVO svo=creatorService.getSubmission(subNo);
		model.addAttribute("svo",svo);
		return "creator/homeworkDetail";
	}
	@GetMapping("/makeCorrect") //정답처리
	public String makeCorrect(@RequestParam("userId")String userId,
			@RequestParam("subScr")String sScr,
			@RequestParam("subNo")String sNo,
			Model model) {
		int subScr=Integer.parseInt(sScr);
		int subNo=Integer.parseInt(sNo);
		int currentScore=creatorService.getUserScore(userId);
		int newScore=currentScore+subScr;
		creatorService.setUserScore(userId,newScore);
		creatorService.setSubmissionScore(subScr,subNo);
		
		return "redirect:/";
	}
	
	
	@PostMapping("/registHomeworkForm")
	public String registHomeworkForm(HomeworkVO vo) {
		creatorService.makeHomework(vo);
		
			
		return "creator/creatorSuccess";
		
		
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
	
	@GetMapping("/refuseRegist")
	public String refuseRegist(@RequestParam("aplyNo")int aplyNo) {
		creatorService.deleteApply(aplyNo);
		
		return "creator/groupApplyList";
	}
	
	@GetMapping("/makeAnswerForm")
	public String getMethodName(@RequestParam("qstnNo")int qstnNo,Model model,Authentication authentication) {
		if (authentication != null) {
			UserAuth userAuth = (UserAuth)authentication.getPrincipal();

			String userId  = userAuth.getUserId();//선생님 ID
			QuestionVO qvo=creatorService.getQuestion(qstnNo);
			
			model.addAttribute("qvo",qvo);
			model.addAttribute("creatorId",userId);
			return "creator/makeAnswer";
			
		}else {
			return "creator/noAuth";
		}
		
	}
	@PostMapping("/registAnswerForm")
	public String registAnswerForm(QuestionVO vo) {
		creatorService.addAnswer(vo);
		
		return "creator/creatorSuccess";
	}
	
	
}
