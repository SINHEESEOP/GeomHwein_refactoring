package com.geomhwein.go.controller;


import java.io.File;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.util.List;
import com.geomhwein.go.command.UserAuthVO;
import com.geomhwein.go.securlty.UserAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.geomhwein.go.command.HomeworkVO;
import com.geomhwein.go.command.QuestionVO;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


import com.geomhwein.go.command.ComunityUploadVO;
import com.geomhwein.go.command.ReplyVO;
import com.geomhwein.go.command.ComunityVO;
import com.geomhwein.go.command.EducationGroupVO;
import com.geomhwein.go.user.service.UserService;
import com.geomhwein.go.util.Criteria;
import com.geomhwein.go.util.PageVO;

import ch.qos.logback.core.status.Status;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Value("${project.upload.path}")
	private String uploadPath;
	
	@GetMapping("/cart")
	public String cart() {
		return "user/cart";
	}

	@GetMapping("/billing")
	public String billing() {
		return "user/billing";
	}
	
	
	

	@GetMapping("/profile")
	public String profile(Authentication authentication, Model model) {

		System.out.println("요청 왔는겨?");

		if (authentication != null) {
			UserAuth userAuth = (UserAuth)authentication.getPrincipal();

			String userId  = userAuth.getUsername();
			String userPwHash = userAuth.getPassword();
			String userRole = userAuth.getRole();

			System.out.println(userId + " " + userPwHash + " " + userRole);
			System.out.println("213231");
			model.addAttribute("role", userRole );

		}

		return "/user/profile";
	}
	
	@Autowired
	@Qualifier("userService")
	private UserService userService;
	

	@GetMapping("/comunityList")
	public String userComunityList(Model model , Criteria cri) {
		
		List<ComunityVO> list = userService.getComunityList(cri);
		
		int total = userService.comunityTotal(cri);
		
		PageVO vo = new PageVO(cri, total);
		
		model.addAttribute("pageVO",vo);
		
		model.addAttribute("list" , list);
		
		return "user/comunityList";
	}
	
	@GetMapping("/comunityDetail")
	public String comunityDetail(@RequestParam("pst_ttl_no") int pst_ttl_no , Model model, HttpServletRequest request , HttpServletResponse response ) {
		
		
		Cookie[] cookies = request.getCookies();
		
		if(cookies != null) {
			
			for(Cookie cookie : cookies) {
				
				if(!cookie.getValue().contains(request.getParameter("pst_ttl_no"))) {
					cookie.setValue(cookie.getValue() + "_" + request.getParameter("pst_ttl_no"));
					cookie.setMaxAge(3600);
					response.addCookie(cookie);
					userService.updateHit(pst_ttl_no);
				}
			}
			
		}else {
			Cookie newcookie = new Cookie("visit_cookie" , request.getParameter("pst_ttl_no"));
			newcookie.setMaxAge(3600);
			response.addCookie(newcookie);
			userService.updateHit(pst_ttl_no);
		}
		
		
		ComunityVO vo = userService.getComunityDetail(pst_ttl_no);
		List<ComunityUploadVO> list = userService.getFile(pst_ttl_no);
		
		model.addAttribute("vo",vo);
		model.addAttribute("list",list); 
		
		return "user/comunityDetail";
	}
	
	@GetMapping("/comunityReg")
	public String comunityReg() {
		return "user/comunityReg";
	}
	
	@GetMapping("/comunityModify")
	public String comunityModify(@RequestParam("pst_ttl_no") int pst_ttl_no , Model model) {
		
		ComunityVO vo = userService.getComunityDetail(pst_ttl_no);
		
		model.addAttribute("vo",vo);
		
		return "user/comunityModify";
	}
	
	@GetMapping("/groupList")
	public String userGroupList(Model model ,Authentication authentication) {
		int gCount=userService.getGroupCount();
		List<EducationGroupVO> groupList=new ArrayList<>();
		if (authentication != null) {
			UserAuth userAuth = (UserAuth)authentication.getPrincipal();

			String userId  = userAuth.getUsername();
			
			model.addAttribute("userName", userId);

		}
		for(int i=1;i<=gCount;i++) {
			groupList.add(userService.getGroup(i));
		}
		model.addAttribute("groupList",groupList);
		return "user/groupList";
	}
	@GetMapping("/eduGroup")
	public String eduGroup  (@RequestParam("username")String username,Model model) {
		model.addAttribute("username",username );
		return "user/eduGroup";
	}
	@GetMapping("/groupApplyList")
	public String groupApplyList() {
		return "user/groupApplyList";
	}
	
	@GetMapping("/questionList")
	public String questionList(Model model,Authentication authentication) {
		if (authentication != null) {
			UserAuth userAuth = (UserAuth)authentication.getPrincipal();

			String userId  = userAuth.getUsername();
			model.addAttribute("userName",userId);

		}
		
		
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

	
	
	@GetMapping("/questionDetail")
	public String questionDetail() {
		return "user/questionDetail";
	}
	
	@GetMapping("/questionModify")
	public String questionModify() {
		return "user/questionModify";
	}
	
	@PostMapping("/comunityForm")
	public String comunityForm(ComunityVO vo , RedirectAttributes rec,
			MultipartHttpServletRequest part) {
		
		
		List<MultipartFile> list = part.getFiles("file");
	

		int result = userService.comunityForm(vo , list);
		
		if(result == 1 ) {
			rec.addFlashAttribute("msg", "등록성공");
		}else {
			rec.addFlashAttribute("msg", "등록실패");
		}
		
		return "redirect:/user/comunityList";
	}
	
	@PostMapping("/comunityModifyForm")
	public String comunityModifyForm(ComunityVO vo , RedirectAttributes rec) {
		
		int result = userService.comunityModifyForm(vo);
		
		if(result == 1 ) {
			rec.addFlashAttribute("msg", "수정성공");
		}else {
			rec.addFlashAttribute("msg", "수정실패");
		}
		
		return "redirect:/user/comunityList";
	}
	
	@GetMapping("/comunityDelete")
	public String comunityDelete(@RequestParam("pst_ttl_no") int pst_ttl_no , RedirectAttributes rec) {
		
		int result = userService.comunityDelete(pst_ttl_no);
		
		if(result == 1 ) {
			rec.addFlashAttribute("msg", "삭제성공");
		}else {
			rec.addFlashAttribute("msg", "삭제실패");
		}
		
		return "redirect:/user/comunityList";
	}

	
	@GetMapping("/attachment")
	@ResponseBody
	public ResponseEntity<File> attachment(@RequestParam("filepath") String filepath , @RequestParam("uuid") String uuid , @RequestParam("filename") String filename
			) {
		
		
			
		 String savepath = uploadPath + "/" + filepath + "/" + uuid + "_" + filename;
		
		 File file = new File(savepath);
		
		 ResponseEntity<File> result = null;
		 
		try {


			HttpHeaders header = new HttpHeaders();
			header.add("Content-Disposition", "attachment; filename=" + filename);
			
		
			result = new ResponseEntity<>(file, header,HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		 return result;
	}
  
  @GetMapping("/groupSelectForm")
	public String groupSelectForm(@RequestParam("groupNo")String gno,Authentication authentication) {
		int groupNo=Integer.parseInt(gno);
		
		if (authentication != null) {
			UserAuth userAuth = (UserAuth)authentication.getPrincipal();

			String username  = userAuth.getUsername();
			
			userService.applyGroup(groupNo,username);

			

		}else {
			
			return "creator/creatorFail";//임시조치
		}
		return "creator/creatorSuccess";//임시조치
		
	}
	
	@PostMapping("/replyAdd")
	@ResponseBody
	public String replayAdd(@RequestBody ReplyVO vo) {
		
		
		userService.replyAdd(vo);
		
		return "success";
	}
	
	
}
