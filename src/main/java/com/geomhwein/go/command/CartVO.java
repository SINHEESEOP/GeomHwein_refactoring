package com.geomhwein.go.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartVO {

	private String contsNm; //
	private String userId; // 유저 ID
	private String userNm; // 강사 이름
	private String userRating; // 강사 등급
	private String contsPrc; // 가격.
	private int recAge;
	private int groupNo;

}
