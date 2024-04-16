package com.geomhwein.go.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class educationGroupVO {

	private UserAuthVO userAuthVO;

	private String groupNm;//그룹명
	private int groupNo;//그룹등록번호(1~6)
	private String userId;//선생님 ID
	private int groupUtztnNope; //그룹 정원
	private String lastCmcrsYmd; //그룹최종이수일
	private String contsNm;//그룹 콘텐츠 명
	private int recAge; //그룹 권장 연령

}
