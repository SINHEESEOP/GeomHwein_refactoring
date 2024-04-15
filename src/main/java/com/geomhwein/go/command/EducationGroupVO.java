package com.geomhwein.go.command;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EducationGroupVO {
	
	private int aplyNo;//그룹신청번호
	private int groupNo;//그룹등록번호(1~6)
	private String userId;
	private int groupUtztnNope; //그룹 정원
	private String lastCmcrsYmd; //그룹최종이수일
	private String contsNm;//그룹 콘텐츠 이름
	private int recAge; //그룹 권장 연령
	private int Active;//신청취소여부
	
}
