package com.geomhwein.go.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	@GetMapping("/")
	public String main() {
		return "main";
	} // 임시로 메인을 billing 으로 함, 메인 완성 시 변경 될 예정.

	@GetMapping("/sign_in")
	public String signIn() {
		return "sign_in";
	}

	@GetMapping("/sign_up")
	public String signUp() {
		return "sign_up";
	}


}
