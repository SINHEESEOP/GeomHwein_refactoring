package com.geomhwein.go.controller;

import com.geomhwein.go.command.UserAuthVO;
import com.geomhwein.go.command.UserDetailsVO;
import com.geomhwein.go.securlty.service.NormalUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainRestController {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	@Qualifier("normalUserService")
	private NormalUserService normalUserService;

	@PostMapping("/signUpForm")
	public ResponseEntity<String> signUpUser(@RequestBody UserAuthVO UserAuthVO) {

		System.out.println(UserAuthVO.toString() );
		UserAuthVO.setUserPwHash( bCryptPasswordEncoder.encode( UserAuthVO.getUserPwHash() ) );

		int result = normalUserService.signUp(UserAuthVO);

		if(result == 1) {
			return new ResponseEntity<>("가입 성공!", HttpStatus.CREATED);
		}

		return new ResponseEntity<>("가입 실패!", HttpStatus.CREATED);
	}

//	@PostMapping("/signInForm")
//	public ResponseEntity<String> signInUser(@RequestBody UserAuthVO UserAuthVO) {
//		System.out.println(UserAuthVO.toString() );
//		return new ResponseEntity<>("성공!", HttpStatus.CREATED);
//	}


}
