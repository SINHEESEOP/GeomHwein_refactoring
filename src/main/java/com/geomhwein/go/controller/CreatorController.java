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
	
	
	//메인
	@GetMapping("/creatorMain")
	public String creatorMain(Model model) {
		List<UserDetailsVO> sList=creatorService.getAllStudent();
		model.addAttribute("studentList",sList);
		
		return "creator/creatorMain";
	}
	
	
	//그룹상세보기
	@GetMapping("/eduGroup")
	public String eduGroup  () {
		
		return "creator/eduGroup";
	}
	
	
	
	
	//질문리스트 보기
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
	//숙제 작성
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
	//숙제제출리스트보기
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
	//숙제 상세보기
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
	
	//숙제등록절차
	@PostMapping("/registHomeworkForm")
	public String registHomeworkForm(HomeworkVO vo) {
		creatorService.makeHomework(vo);
		
			
		return "creator/creatorSuccess";
		
		
	}
	//그룹신청목록보기
	@GetMapping("/groupApplyList")
	public String groupApplyList(Model model) {
		int applyCount=creatorService.getApplyCount();
		if(applyCount==0) {
			return "redirect:/";
		}
		List<EducationGroupVO>applyList=creatorService.getApply();
		
		model.addAttribute("applyList",applyList);
		return "creator/groupApplyList";
	}
	
	//신청반려
	@GetMapping("/refuseRegist")
	public String refuseRegist(@RequestParam("aplyNo")String aNo) {
		int aplyNo=Integer.parseInt(aNo);
		creatorService.deleteApply(aplyNo);
		
		return "redirect:/";
	}
	
	//신청승인
	@GetMapping("/acceptRegist")
	public String acceptRegitst(@RequestParam("aplyNo")String aNo,@RequestParam("groupNo")String gNo) {
		int aplyNo=Integer.parseInt(aNo);
		int groupNo=Integer.parseInt(gNo);
		creatorService.setApplyStatus(aplyNo);
		creatorService.setGroupUtztnNope(groupNo);
		return "redirect:/";
	}
	
	//답변주기
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
	
	//답변등록절차
	@PostMapping("/registAnswerForm")
	public String registAnswerForm(QuestionVO vo) {
		creatorService.addAnswer(vo);
		creatorService.setQstnStatus(vo);
		return "creator/creatorSuccess";
	}
	
	
}
