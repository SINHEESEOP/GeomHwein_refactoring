package com.geomhwein.go.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupApplicationVO {

	private Integer aplyNo;
	private Integer groupNo;
	private String userId;
	private String aplyYmd;
	private Character aplyStatus;
	private int groupUtztnNope; //그룹 정원
	private String lastCmcrsYmd; //그룹최종이수일
	private String contsNm;//그룹 콘텐츠 이름
	private int recAge; //그룹 권장 연령
}
