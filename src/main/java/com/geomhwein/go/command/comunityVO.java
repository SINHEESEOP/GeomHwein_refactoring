package com.geomhwein.go.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class comunityVO {
	
	private Integer pst_ttl_no; //글번호
	private String pst_ttl_nm; //글제목
	private String pst_ttl_cn; //글내용
	private Integer inq_cnt; //조회수
	private String pst_regdate; //작성일
	private String userName; //작성자
	
}
