package com.geomhwein.go.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class comunityVO {
	
	private Integer pstTtlNo; //글번호
	private String pstTtlNm; //글제목
	private String pstTtlCn; //글내용
	private Integer inqCnt; //조회수
	private String pstRegDate; //작성일
	private String userName; //작성자
	
}
