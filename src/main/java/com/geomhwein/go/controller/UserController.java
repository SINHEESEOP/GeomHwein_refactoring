package com.geomhwein.go.controller;


import java.io.File;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.geomhwein.go.command.*;
import com.geomhwein.go.securlty.service.NormalUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.geomhwein.go.command.UserAuthVO;
import com.geomhwein.go.command.UserDetailsVO;
import com.geomhwein.go.securlty.UserAuth;
import org.springframework.security.core.Authentication;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.geomhwein.go.command.ComunityUploadVO;
import com.geomhwein.go.command.ReplyVO;
import com.geomhwein.go.command.SubmissionVO;
import com.geomhwein.go.command.ComunityVO;
import com.geomhwein.go.command.EducationGroupVO;
import com.geomhwein.go.command.GroupApplicationVO;
import com.geomhwein.go.user.service.UserService;
import com.geomhwein.go.util.Criteria;
import com.geomhwein.go.util.PageVO;

@Controller
@RequestMapping("/user")
public class UserController {
	
	String cookieValue = "";
	
	@Autowired
	@Qualifier("userService")
	private UserService userService;

	@Autowired
	private NormalUserMapper normalUserService;

	@Value("${project.upload.path}")
	private String uploadPath;


	@GetMapping("/profile")
	public String profile(Authentication authentication, Model model) {
		if (authentication != null) {
			UserAuth userAuth = (UserAuth) authentication.getPrincipal();
			model.addAttribute("role", userAuth.getRole());

			List<EducationGroupVO> userEduList = userService.getAllEducationGroup(userAuth.getUserId());
			System.out.println("리스트 숫자 : " + userEduList.size());

			for (int i = 0; i < userEduList.size(); i++) {
				String time = userEduList.get(i).getContentVO().getUtztnBgngYmd().substring(0, 10);
				userEduList.get(i).getContentVO().setUtztnBgngYmd(time);
				System.out.println(time);
			}

			model.addAttribute("userEduList", userEduList);
		}

		return "user/profile";
	}

	@GetMapping("/comunityList")
	public String userComunityList(Model model, Criteria cri) {

		List<ComunityVO> list = userService.getComunityList(cri);

		System.out.println(list.toString());
		int total = userService.comunityTotal(cri);

		PageVO vo = new PageVO(cri, total);

		model.addAttribute("pageVO", vo);

		model.addAttribute("list", list);

		return "user/comunityList";
	}

	@GetMapping("/comunityDetail")
	public String comunityDetail(@RequestParam("pst_ttl_no") int pst_ttl_no, Model model, HttpServletRequest request, HttpServletResponse response) {


		Cookie[] cookies = request.getCookies();
    
		String pstTtlNo = request.getParameter("pst_ttl_no");
		
		if (cookies != null) {
		    boolean found = false;
		    for (Cookie cookie : cookies) {
		        if (cookie.getName().equals("visit_cookie") && cookie.getValue().contains(pstTtlNo)) {
		        	cookieValue = cookie.getValue();
		        	found = true;
		            break;
		        }
		    }
		    if (!found) {
		        Cookie newCookie = new Cookie("visit_cookie", cookieValue + pstTtlNo);
		        newCookie.setMaxAge(3600);
		        response.addCookie(newCookie);
		        userService.updateHit(pst_ttl_no);
		    }
		} else {
		    Cookie newCookie = new Cookie("visit_cookie", pstTtlNo);
		    newCookie.setMaxAge(3600);
		    response.addCookie(newCookie);
		    userService.updateHit(pst_ttl_no);
		}
		
		ComunityVO vo = userService.getComunityDetail(pst_ttl_no);
		List<ComunityUploadVO> list = userService.getFile(pst_ttl_no);

		model.addAttribute("vo", vo);
		model.addAttribute("list", list);

		return "user/comunityDetail";
	}

	@GetMapping("/comunityReg")
	public String comunityReg() {
		return "user/comunityReg";
	}

	@GetMapping("/comunityModify")
	public String comunityModify(@RequestParam("pst_ttl_no") int pst_ttl_no, Model model) {

		ComunityVO vo = userService.getComunityDetail(pst_ttl_no);

		List<ComunityUploadVO> list = userService.getFile(pst_ttl_no);

		model.addAttribute("vo", vo);
		model.addAttribute("list", list);

		return "user/comunityModify";
	}

	@GetMapping("/groupList")
	public String userGroupList(Model model, Authentication authentication) {

		List<EducationGroupVO> groupList = userService.getGroup();
		if (authentication != null) {
			UserAuth userAuth = (UserAuth) authentication.getPrincipal();

			String userId = userAuth.getUserId();

			model.addAttribute("userName", userId);

		}


		model.addAttribute("groupList", groupList);
		return "user/groupList";
	}

	@GetMapping("/eduGroup")
	public String eduGroup(@RequestParam("username") String username, Model model) {
		model.addAttribute("username", username);
		return "user/eduGroup";
	}

	@GetMapping("/groupApplyList")
	public String groupApplyList(Authentication authentication , Model model) {
		
		UserAuth userAuth = (UserAuth)authentication.getPrincipal();
		
		String userId = userAuth.getUserId();
		
		List<GroupApplicationVO> list = userService.getGroupApplyList(userId);

		model.addAttribute("list", list);

		return "user/groupApplyList";
	}


	@GetMapping("/groupApplyDetail")
	public String groupApplyDetail(@RequestParam("groupno") int groupNo, Model model , Authentication authentication) {
		
		UserAuth userAuth = (UserAuth)authentication.getPrincipal();
		
		String userId = userAuth.getUserId();
		
		EducationGroupVO vo = userService.getGroupOne(groupNo);
		List<QuestionVO> list = userService.getQuestionList(userId);
		List<HomeworkVO> list2 = userService.getHomeworkList(vo.getUserId());

		model.addAttribute("vo", vo);
		model.addAttribute("list", list);
		model.addAttribute("list2", list2);

		return "user/groupApplyDetail";
	}

	@GetMapping("/viewHomework")
	public String homeworkList(Model model, @RequestParam("userId") String userId) {
		List<HomeworkVO> list = userService.getHomeworkList(userId);
		model.addAttribute("homeworkList", list);
		return "user/HomeworkReg";

		//숙제조회 클릭시 다시 화면으로 값 model에 담아서 List<HomeworkVO> 로 보냄
		//타임리프 반복문 돌려서 화면에서 띄워주면됨
	}

	@GetMapping("homeworkList")
	public String homeworkList() {
		return "user/homeworkList";
	}


	@GetMapping("/questionDetail")
	public String questionDetail(@RequestParam("qstnno") int qstn_no, Model model) {

		System.out.println(qstn_no);

		QuestionVO vo = userService.questionDetail(qstn_no);

		model.addAttribute("vo", vo);

		return "user/questionDetail";
	}

	@GetMapping("/questionModify")
	public String questionModify(@RequestParam("qstnno") int qstnno, Model model) {

		QuestionVO vo = userService.questionDetail(qstnno);
		model.addAttribute("vo", vo);

		return "user/questionModify";
	}

	@PostMapping("/comunityForm")
	public String comunityForm(ComunityVO vo , RedirectAttributes rec,
			MultipartHttpServletRequest part , Authentication authentication) {
		
		
		UserAuth userauth = (UserAuth)authentication.getPrincipal();
		String userId = userauth.getUserId();
		
		List<MultipartFile> list = part.getFiles("file");
		
	
		int result = userService.comunityForm(vo , list , userId);
		
		if(result == 1 ) {
			rec.addFlashAttribute("msg", "등록성공");
		} else {
			rec.addFlashAttribute("msg", "등록실패");
		}

		return "redirect:/user/comunityList";
	}


	@PostMapping("/comunityModifyForm")
	public String comunityModifyForm(ComunityVO vo , RedirectAttributes rec
									, MultipartHttpServletRequest part,Authentication authentication) {
		
		List<MultipartFile> list = part.getFiles("file");
		
		UserAuth userauth = (UserAuth)authentication.getPrincipal();
		
		String userId = userauth.getUserId();
		int result = userService.comunityModifyForm(vo,list, userId);
		
		if(result == 1 ) {
			rec.addFlashAttribute("msg", "수정성공");
		} else {
			rec.addFlashAttribute("msg", "수정실패");
		}
		
		return "redirect:/user/comunityList";
	}

	@GetMapping("/comunityDelete")
	public String comunityDelete(@RequestParam("pst_ttl_no") int pst_ttl_no, RedirectAttributes rec) {

		int result = userService.comunityDelete(pst_ttl_no);

		if (result == 1) {
			rec.addFlashAttribute("msg", "삭제성공");
			userService.deleteFile(pst_ttl_no);
		}else {
			rec.addFlashAttribute("msg", "삭제실패");
		}
		return "redirect:/user/comunityList";
	}


	@GetMapping("/attachment")
	@ResponseBody
	public ResponseEntity<File> attachment(@RequestParam("filepath") String filepath, @RequestParam("uuid") String uuid, @RequestParam("filename") String filename
	) {


		String savepath = uploadPath + "/" + filepath + "/" + uuid + "_" + filename;

		File file = new File(savepath);

		ResponseEntity<File> result = null;

		try {


			HttpHeaders header = new HttpHeaders();
			header.add("Content-Disposition", "attachment; filename=" + URLEncoder.encode(filename, "utf-8"));

			result = new ResponseEntity<>(file, header, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	@PostMapping("/replyAdd")
	@ResponseBody
	@CrossOrigin("*")
	public String replayAdd(@RequestBody ReplyVO vo, Authentication authentication) {


		int pst_ttl_no = vo.getPstTtlNo();

		if (authentication != null) {
			UserAuth userAuth = (UserAuth) authentication.getPrincipal();
			String userId = userAuth.getUserId();
			vo.setUserId(userId);

			userService.replyAdd(vo);
			userService.replyCount(pst_ttl_no);
		}


		return "success";
	}

	@GetMapping("/groupSelectForm")
	public String groupSelectForm(@RequestParam("groupNo") String gno, Authentication authentication) {
		int groupNo = Integer.parseInt(gno);

		if (authentication != null) {
			UserAuth userAuth = (UserAuth) authentication.getPrincipal();

			String username = userAuth.getUserId();

			String userId = username;
			List<GroupApplicationVO> list = userService.getGroupApplyList(userId);
			if (list.size() > 0) {
				return "redirect:/";
			} else {
				userService.applyGroup(groupNo, username);

			}
		} else {

			return "creator/creatorFail";//임시조치
		}
		return "redirect:/user/groupApplyList";//임시조치
	}

	@GetMapping("/questionReg")
	public String questionReg(@RequestParam("groupNo") int groupNo, Model model) {


		System.out.println(groupNo);

		model.addAttribute("groupNo", groupNo);

		return "user/questionReg";
	}

	@PostMapping("/questionForm")
	@ResponseBody
	public String questionForm(QuestionVO vo) {

		System.out.println(vo.toString());

		userService.addQuestion(vo);

		return "success";
	}

	@PostMapping("/questionModifyForm")
	public String questionModifyForm(QuestionVO vo) {


		userService.questionModifyForm(vo);

		int groupno = vo.getGroupNo();


		return "redirect:/user/groupApplyDetail?groupno=" + groupno;
	}


	@GetMapping("questionDelete")
	public String deleteQuestion(@RequestParam("qstnno") int qstnno, @RequestParam("groupno") int groupno) {

		userService.deleteQuestion(qstnno);

		return "redirect:/user/groupApplyDetail?groupno=" + groupno;
	}

	@GetMapping("/getReplyList")
	@ResponseBody
	public List<ReplyVO> getReplyList(@RequestParam("pst_ttl_no") int pst_ttl_no) {

		List<ReplyVO> list = userService.getReplyList(pst_ttl_no);

		return list;
	}

	@PostMapping("/replyUpdate")
	@ResponseBody
	public String replyUpdate(@RequestBody ReplyVO vo) {

		userService.replyUpdate(vo);

		System.out.println(vo.toString());

		return "success";
	}

	@PostMapping("/replyDelete")
	@ResponseBody
	public String replyDelete(@RequestBody @RequestParam("reply_no") int reply_no, @RequestParam("pst_ttl_no") int pst_ttl_no) {

		List<ReplyVO> list = userService.replyFilter(reply_no);

		if (list.size() == 0) {
			userService.replyDelete(reply_no);
			userService.deleteCount(pst_ttl_no);
		} else {
			userService.replyStatus(reply_no);
			userService.deleteCount(pst_ttl_no);

		}


		return "success";
	}

	@PostMapping("/getChildList")
	@ResponseBody
	public List<ReplyVO> getChildList(@RequestParam("pst_ttl_no") int pst_ttl_no, @RequestParam("parent_reply_no") int parent_reply_no) {

		List<ReplyVO> list = userService.getChildList(parent_reply_no);

		return list;
	}

	@GetMapping("/showAnswer")
	public String showAnswer(@RequestParam("qstnNo") int qstnNo, Model model, @RequestParam("userId") String creatorId) {

		QuestionVO vo = userService.getAnswer(qstnNo);


		model.addAttribute("vo", vo);
		model.addAttribute("creatorId", creatorId);

		return "user/showAnswer";
	}

	@GetMapping("/homeworkReg")
	public String homeworkReg(@RequestParam("asmtNo") int asmtNo, Model model, Authentication authentication) {

		UserAuth userAuth = (UserAuth) authentication.getPrincipal();
		String userId = userAuth.getUserId();

		HomeworkVO vo = userService.homeworkReg(asmtNo);
		SubmissionVO svo = userService.getSubmission(userId, asmtNo);

		model.addAttribute("vo", vo);
		model.addAttribute("svo", svo);

		return "user/homeworkReg";
	}

	@PostMapping("homeworkRegForm")
	public String homeworkRegForm(SubmissionVO vo, Authentication authentication) {


		UserAuth userAuth = (UserAuth) authentication.getPrincipal();
		String userId = userAuth.getUserId();
		vo.setUserId(userId);

		userService.submissionForm(vo);

		return "redirect:/user/homeworkReg?asmtNo=" + vo.getAsmtNo();
	}

	@PostMapping("homeworkUpdate")
	public String homeworkUpdate(SubmissionVO vo) {

		System.out.println(vo.toString() + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		userService.submissionUpdate(vo);

		return "redirect:/user/homeworkReg?asmtNo=" + vo.getAsmtNo();
	}

	@GetMapping("/groupProgress")
	public String groupProgress(Model model) {
		List<UserDetailsVO> userList = userService.getUserScoreList();
		System.out.println(userList.toString());
		model.addAttribute("userList", userList);

		return "user/groupProgress";
	}


	//장바구니 담기
	@PostMapping("/addOnBasket")
	@ResponseBody
	public void addOnBasket(@RequestParam("groupNo") String gNo, Authentication authentication) {
		int groupNo = Integer.parseInt(gNo);
		if (authentication != null) {
			UserAuth userAuth = (UserAuth) authentication.getPrincipal();

			String userId = userAuth.getUserId();

			userService.addBasket(groupNo, userId);
		}

	}

	//교육자승급신청
	@GetMapping("/registCreator")
	public String registCreator(Authentication authentication, Model model) {
		String userId = null;
		if (authentication != null) {
			UserAuth userAuth = (UserAuth) authentication.getPrincipal();

			userId = userAuth.getUserId();

		}
		model.addAttribute("userId", userId);
		return "user/regBeingCreator";
	}

	@PostMapping("/creatorRegForm")
	public String creatorRegForm(EvaluationVO vo) {
		userService.registCreator(vo);

		return "user/profile";
	}
	
	@GetMapping("/applyCancle")
	@ResponseBody
	public String applyCancle(@RequestParam("aplyno") int aplyno) {
		
		userService.applyCancle(aplyno);
		
		return "success";
	}
	

	@GetMapping("/cart")
	public String cart(Model model, Authentication auth) {

		if(auth != null) {
			UserAuth userAuth = (UserAuth) auth.getPrincipal();
			ArrayList<CartVO> carts = normalUserService.getCart(userAuth.getUserId());

			int sum = 0;
			for (CartVO cart : carts) {
				cart.setContsPrc(cart.getContsPrc().replaceAll("\\.00$", ""));
				sum += Integer.parseInt(cart.getContsPrc());
			}

			model.addAttribute("carts", carts);
			model.addAttribute("sum", sum);
		}

		return "user/cart";
	}


}
