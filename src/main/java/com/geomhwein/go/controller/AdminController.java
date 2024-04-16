package com.geomhwein.go.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.geomhwein.go.command.AdminVO;
import com.geomhwein.go.service.AdminService;

//@Controller
//public class AdminController {
//	
//	
//	@Autowired
//	@Qualifier("AdminService")
//	private AdminService adminService;
//	
//	
//
//	@GetMapping("mttrInsert")
//	public String mttrInsert() {
//		
//		return "/admin/mttrInsert";
//	}
//	
//	
//	@PostMapping("/insertForm")
//	public String insertPage(AdminVO vo,RedirectAttributes ra) {
//		System.out.println();
//		int result = adminService.mttrInsert(vo);
//		if(result == 1) {
//			ra.addFlashAttribute("msg", "상담내역이 등록되었습니다");
//		}else {
//			
//			ra.addFlashAttribute("msg", "상담내역 등록에 실패했습니다.");
//		}
//		
//		return "mttr";
//	}
//	
//}
