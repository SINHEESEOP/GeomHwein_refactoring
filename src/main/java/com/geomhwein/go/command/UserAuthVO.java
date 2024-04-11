package com.geomhwein.go.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserAuthVO {

	private UserDetailsVO userDetailsVO;
	private String userId;
	private String userPwHash;
	private String userNm;
	private String salt;
	private String authToken;
	private LocalDateTime tokenExpiration;
	private String refreshToken;
	private String refreshTokenExpiration;

}
