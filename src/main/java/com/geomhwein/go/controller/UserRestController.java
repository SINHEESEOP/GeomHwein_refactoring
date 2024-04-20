package com.geomhwein.go.controller;

import com.geomhwein.go.command.ContentVO;
import com.geomhwein.go.command.UserDetailsVO;
import com.geomhwein.go.securlty.UserAuth;
import com.geomhwein.go.user.service.UserMapper;
import com.geomhwein.go.user.service.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserRestController {

	@Autowired
	private UserService userService;
	@Autowired
	private UserMapper userMapper;

	@PostMapping("/pfBring")
	public ResponseEntity<UserDetailsVO> pfBring(Authentication authentication) {

		if (authentication != null) {
			UserAuth userAuth = (UserAuth) authentication.getPrincipal();
			UserDetailsVO userDetailsVO = userService.getUserDetails(userAuth.getUserId());
			userDetailsVO.setUserNm(userAuth.getUsername());

			return ResponseEntity.ok(userDetailsVO);
		}
		return ResponseEntity.badRequest().body(null);
	}

	@PostMapping("/pfUpdate")
	public ResponseEntity<String> pfUpdate(Authentication authentication,
	                                       UserDetailsVO userDetailsVO) {

		System.out.println("업데이트 함수 실행됬는감??");
		System.out.println(userDetailsVO.toString() );

		try {
			UserAuth userAuth = (UserAuth) authentication.getPrincipal();
			userDetailsVO.setUserId(userAuth.getUserId() );
			userService.updateProfile(userDetailsVO);
			return ResponseEntity.ok("프로필 업데이트 성공");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("프로필 업데이트 실패: " + e.getMessage());
		}

	}

}
