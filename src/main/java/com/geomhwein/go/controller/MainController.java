package com.geomhwein.go.controller;

import com.geomhwein.go.securlty.UserAuth;
import com.geomhwein.go.securlty.service.NormalUserService;
import com.geomhwein.go.util.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

	@Autowired
	private NormalUserService normalUserService;

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

	@GetMapping("/mttr")
	public String mttr() {

		return "mttr";
	}


}


