package com.geomhwein.go.controller;

import com.geomhwein.go.command.UserAuthVO;
import com.geomhwein.go.command.UserDetailsVO;
import com.geomhwein.go.securlty.UserAuth;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

	@GetMapping("/")
	public String main(Authentication auth, Model model) {

		if (auth != null) {
//			System.out.println("아이디능??" + auth.getName() );
//			model.addAttribute("user", auth.getName());

			UserAuth userAuth = (UserAuth)auth.getPrincipal();

			String userId  = userAuth.getUsername();
			String userPwHash = userAuth.getPassword();
			String userRole = userAuth.getRole();

			System.out.println(userId + " " + userPwHash + " " + userRole);
			System.out.println("213231");
			model.addAttribute("role2", userRole );

		}

		return "main";
	} // 임시로 메인을 billing 으로 함, 메인 완성 시 변경 될 예정.

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

	@GetMapping("/mttr")
	public String mttr() {
		
		return "mttr";
	}



}
	

