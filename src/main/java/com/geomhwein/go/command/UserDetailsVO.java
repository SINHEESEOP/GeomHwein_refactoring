package com.geomhwein.go.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDetailsVO {

	private ContentVO contentVO;
	private EducationGroupVO educationGroupVO;
	private String userId; // VARCHAR(100) NOT NULL
	private String userTelno; // VARCHAR(20)  // 50으로 변경예정
	private String userEmlAddr; // VARCHAR(100) NOT NULL
	private Integer userAge; // INT
	private Integer userRating; // DECIMAL(5,2)
	private String gender; // ENUM('male', 'female', 'other')
	private String address; // TEXT
	private String profilePictureFilename; // TEXT
	private String userRole; // VARCHAR(20)
	private Boolean isActive; // TINYINT(1)
	private LocalDateTime deactivatedAt; // DATETIME
	private LocalDateTime deletionRequestedAt; // DATETIME DEFAULT NULL
	private Integer failedLoginAttempts; // INT DEFAULT 0
	private LocalDateTime lastLoginDatetime; // DATETIME
	private Integer userScr;//숙제점수
	private String Interests;
	private String userNm;

	public UserDetailsVO(String userId, String Interests) {
		this.userId = userId;
		this.userTelno = Interests;
	}
}
