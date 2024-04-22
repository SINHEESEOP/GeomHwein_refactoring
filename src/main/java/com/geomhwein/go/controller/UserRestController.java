package com.geomhwein.go.controller;

import com.geomhwein.go.command.ContentVO;
import com.geomhwein.go.command.UserDetailsVO;
import com.geomhwein.go.securlty.UserAuth;
import com.geomhwein.go.securlty.service.NormalUserMapper;
import com.geomhwein.go.securlty.service.NormalUserService;
import com.geomhwein.go.user.service.UserMapper;
import com.geomhwein.go.user.service.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
	private NormalUserService normalUserService;

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
		System.out.println(userDetailsVO.toString());

		try {
			UserAuth userAuth = (UserAuth) authentication.getPrincipal();
			userDetailsVO.setUserId(userAuth.getUserId());
			userService.updateProfile(userDetailsVO);
			return ResponseEntity.ok("프로필 업데이트 성공");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("프로필 업데이트 실패: " + e.getMessage());
		}

	}

	@PostMapping("/cart/delete/{id}")
	public ResponseEntity<?> deleteCartItem(@PathVariable("id") Long groupNo,
	                                        Authentication auth) {

		UserAuth userAuth = (UserAuth) auth.getPrincipal();

		try {
			normalUserService.deleteCart(groupNo, userAuth.getUserId());  // 서비스 레이어에서 삭제 로직 처리
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting item");
		}

	}

	@PostMapping("/cart/purchase")
	public ResponseEntity<?> purchaseItems(@RequestBody List<Long> itemIds) {
		try {
//			cartService.purchaseItems(itemIds);  // 서비스 레이어에서 구매 로직 처리
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Error during purchase: " + e.getMessage());
		}
	}


}
