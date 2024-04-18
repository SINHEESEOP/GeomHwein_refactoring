package com.geomhwein.go.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HomeworkVO {
	
	
	private String userId; //작성자 혹은 제출자 상황에 맞게 사용
	private String asmtGrd;//숙제 난이도
	private String asmtNm;//숙제 제목
	private String asmtCn;//숙제 내용
	private int asmtNo;//숙제 등록번호
	private int asmtScr;//숙제 점수
	
	
}
