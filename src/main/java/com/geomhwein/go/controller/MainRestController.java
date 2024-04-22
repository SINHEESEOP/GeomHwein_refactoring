package com.geomhwein.go.controller;

import com.geomhwein.go.command.ContentVO;
import com.geomhwein.go.command.UserAuthVO;
import com.geomhwein.go.command.EducationGroupVO;
import com.geomhwein.go.command.UserDetailsVO;
import com.geomhwein.go.recommendation.service.RecommendationService;
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
	private RecommendationService recommendationService;

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

	@PostMapping("/mainContent")
	public ResponseEntity<Map<String, Object>> mainContent(Authentication auth, @RequestBody Criteria cri) {
		Map<String, Object> response = new HashMap<>();

		if (auth != null) {
			UserAuth userAuth = (UserAuth) auth.getPrincipal();
			String role = userAuth.getRole();
			response.put("role", role); // 역할 정보를 맵에 추가
			System.out.println("Role: " + role);
		}

		List<EducationGroupVO> results = normalUserService.getList(cri);
		response.put("educationGroups", new ArrayList<>(results)); // 교육 그룹 리스트를 맵에 추가

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}


	@PostMapping("/mainContent2")
	public ResponseEntity<Map<String, Object>> mainContent2(Authentication auth, @RequestBody Criteria cri) {
		Map<String, Object> response = new HashMap<>();

		if (auth != null) {
			UserAuth userAuth = (UserAuth) auth.getPrincipal();
			String role = userAuth.getRole();
			response.put("role", role); // 역할 정보를 맵에 추가
			System.out.println("Role: " + role);
		}

		List<EducationGroupVO> results = normalUserService.getList2(cri);
		response.put("educationGroups", new ArrayList<>(results)); // 교육 그룹 리스트를 맵에 추가

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@GetMapping("/contents")
	public ResponseEntity<List<ContentVO>> getProcessedContents() {
		// ContentService에서 전처리된 컨텐츠 데이터를 가져옵니다.
		List<ContentVO> processedContents = recommendationService.getProcessedContents();
		return ResponseEntity.ok(processedContents);
	}

	@GetMapping("/users")
	public ResponseEntity<List<UserDetailsVO>> getProcessedUserDetails() {
		// UserService에서 전처리된 사용자 데이터를 가져옵니다.
		List<UserDetailsVO> processedUserDetails = recommendationService.getProcessedUserInterests();
		return ResponseEntity.ok(processedUserDetails);
	}

}
