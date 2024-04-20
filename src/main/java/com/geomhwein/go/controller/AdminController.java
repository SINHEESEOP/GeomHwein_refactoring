package com.geomhwein.go.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.geomhwein.go.admin.service.AdminService;
import com.geomhwein.go.command.AdminVO;
import com.geomhwein.go.command.ContentVO;
import com.geomhwein.go.command.UserDetailsVO;




@Controller
public class AdminController {
	
	
	@Autowired
	@Qualifier("AdminService")
	private AdminService adminService;
	
	

	@GetMapping("mttrInsert")
	public String mttrInsert() {
		
		return "/admin/mttrInsert";
	}
	
	
	@PostMapping("/insertForm")
	public String insertPage(AdminVO vo) {
		adminService.mttrInsert(vo);
		
		
		return "redirect:/mttrList";
	}
	
	
	@GetMapping("/mttrList")
	public String mttrList(Model mo){
		
		ArrayList<AdminVO> mttrList = adminService.mttrList();
		
		
		
		mo.addAttribute("mttrList", mttrList);
		
		
		return "/mttrList";
	}
	
	@GetMapping("/mttrDetail")
	public String mttrDetail (@RequestParam("mttrSn") int mttrSn,Model mo) {
		
		
		
		AdminVO vo = adminService.mttrDetail(mttrSn);
		
		mo.addAttribute("vo",vo);
		
		
		return "mttrDetail";
	}
	
	@PostMapping("/deleteForm")
	public String deleteMttr(@RequestParam("mttrSn") int mttrSn) {
		
		adminService.deleteMttr(mttrSn);
		
		
		return "redirect:/mttrList";
	}
	
	
	@GetMapping("/admin/AllUserList")
	public String AllUserList(Model mo) {
		
		ArrayList<UserDetailsVO> AllUserList = adminService.AllUserList();
		mo.addAttribute("AllUserList", AllUserList);
		
		return "admin/AllUserList";
	}
	
	
	@GetMapping("/admin/contentPage")
	public String ContentPage() {
		
		return "admin/contentPage";
	}
	
	@GetMapping("/user/contentList")
	public String contentList(Model mo){
		
		ArrayList<ContentVO> contentList = adminService.ContentList();
		mo.addAttribute("ContentList", contentList);
		
		return "user/contentList";
	}
	
	
	}

