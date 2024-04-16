package com.geomhwein.go.controller;

import com.geomhwein.go.command.UserAuthVO;
import com.geomhwein.go.command.EducationGroupVO;
import com.geomhwein.go.securlty.UserAuth;
import com.geomhwein.go.securlty.service.NormalUserService;
import com.geomhwein.go.util.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MainRestController {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	@Qualifier("normalUserService")
	private NormalUserService normalUserService;

	@PostMapping("/signUpForm")
	public ResponseEntity<String> signUpUser(@RequestBody UserAuthVO UserAuthVO) {

		System.out.println(UserAuthVO.toString());
		UserAuthVO.setUserPwHash(bCryptPasswordEncoder.encode(UserAuthVO.getUserPwHash()));

		int result = normalUserService.signUp(UserAuthVO);

		if (result == 1) {
			return new ResponseEntity<>("가입 성공!", HttpStatus.CREATED);
		}

		return new ResponseEntity<>("가입 실패!", HttpStatus.CREATED);
	}


	@PostMapping("/mainContent")
	public ResponseEntity<Map<String, Object>> mainContent(Authentication auth, @RequestBody Criteria cri) {
		Map<String, Object> response = new HashMap<>();

		if (auth != null) {
			UserAuth userAuth = (UserAuth) auth.getPrincipal();
			String userNm = userAuth.getUsername();
			response.put("userNm", userNm);
			System.out.println("Role: " + userNm);
		}

		List<EducationGroupVO> results = normalUserService.getList(cri);
		response.put("educationGroups", new ArrayList<>(results)); // 교육 그룹 리스트를 맵에 추가

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}


}
