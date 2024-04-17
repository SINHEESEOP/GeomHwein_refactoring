package com.geomhwein.go.controller;

import com.geomhwein.go.command.ContentVO;
import com.geomhwein.go.command.UserDetailsVO;
import com.geomhwein.go.securlty.UserAuth;
import com.geomhwein.go.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserRestController {

	@Autowired
	private UserService userService;

	@PostMapping("/pfBring")
	public ResponseEntity<UserDetailsVO> pfBring(Authentication authentication) {

		if (authentication != null) {
			UserAuth userAuth = (UserAuth) authentication.getPrincipal();
			UserDetailsVO userDetailsVO = userService.getUserDetails(userAuth.getUserId());
			userDetailsVO.setUserNm(userAuth.getUsername());
			return ResponseEntity.ok(userDetailsVO);
		}
		return ResponseEntity.ok(null);
	}

}
