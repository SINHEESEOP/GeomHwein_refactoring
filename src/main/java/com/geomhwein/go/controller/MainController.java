package com.geomhwein.go.controller;

import com.geomhwein.go.admin.service.AdminService;
import com.geomhwein.go.command.AdminVO;
import com.geomhwein.go.command.ContentUploadVO;
import com.geomhwein.go.command.ContentVO;
import com.geomhwein.go.securlty.UserAuth;
import com.geomhwein.go.securlty.service.NormalUserService;
import com.geomhwein.go.util.Criteria;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

	@Autowired
	private NormalUserService normalUserService;
	
	@Value("${project.upload.path}")
	private String uploadPath;
	
	
	@Autowired
	@Qualifier("AdminService")
	private AdminService adminService;
	

	@GetMapping("/")
	public String main(Authentication auth, Model model, Criteria cri) {

		if (auth != null) {
			UserAuth userAuth = (UserAuth)auth.getPrincipal();

			System.out.println(userAuth.getUsername() + " " + userAuth.getPassword()
						+ " " + userAuth.getRole() );

			model.addAttribute("role2", userAuth.getRole() );
		}

		return "main";
	}

	@GetMapping("/sign_in")
	public String signIn(@RequestParam(value = "err", required = false) String err,
	                     Model model) {

		if (err != null) {
			model.addAttribute("msg", "아이디 또는 비밀번호를 확인하세요.");
		}

		return "sign_in";
	}


	@GetMapping("/sign_up")
	public String signUp() {
		return "sign_up";
	}

	
	@GetMapping("/contentList")
	public String contentList(Model mo){
		
		ArrayList<ContentVO> contentList = adminService.ContentList();
		
		
		mo.addAttribute("ContentList", contentList);
	
		
		return "contentList";
	}
	
	@GetMapping("/display/{filepath}/{uuid}/{filename}")
	@ResponseBody
	public ResponseEntity<byte[]> display(@PathVariable("filepath") String filepath,
										@PathVariable("uuid") String uuid,
										@PathVariable("filename") String filename
								       ){
		System.out.println(filepath);
		System.out.println(filename);
		System.out.println(uuid);
		
		ResponseEntity<byte[]> entity = null;
		try {
			
			String thumbnailFileName = uuid + "_thumb_" + filename; // 썸네일 파일명 
			String savePath = uploadPath + "\\" + filepath + "\\" + thumbnailFileName; 
			
			System.out.println(savePath);
			
			File file = new File(savePath);
			
			System.out.println(file);
			
			byte[] arr = FileCopyUtils.copyToByteArray(file);
					
			HttpHeaders header = new HttpHeaders();
			header.add("Content-type", Files.probeContentType(file.toPath()));

			entity = new ResponseEntity<>(arr, header,HttpStatus.SC_OK);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return entity;
	}
	
	@GetMapping("/mttrList")
	public String mttrList(Model mo){
		
		ArrayList<AdminVO> mttrList = adminService.mttrList();
		
		
		
		mo.addAttribute("mttrList", mttrList);
		
		
		return "mttrList";
	}
	
	@GetMapping("mttrDetail")
	public String mttrDetail (@RequestParam("mttrSn") int mttrSn,Model mo) {
		
		
		
		AdminVO vo = adminService.mttrDetail(mttrSn);
		
		mo.addAttribute("vo",vo);
		
		
		return "/mttrDetail";
	}
	
	@PostMapping("/deleteForm")
	public String deleteMttr(@RequestParam("mttrSn") int mttrSn) {
		
		adminService.deleteMttr(mttrSn);
		
		
		return "redirect:/mttrList";
		
	}
	

}


