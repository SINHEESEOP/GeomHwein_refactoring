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
public class QuestionVO {
	
	private String userId;//질문- 학생 ID // 답변 - 선생님 ID
	private String ansCn;//답변내용
	private String ansYmd;//답변일자
	private int ansNo;//답변등록번호
	private int qstnNo;//질문등록번호
	private String qstnCn;//질문내용
	private String qstnYmd;//질문일자
	private Character qstnStatus;
	private int groupNo;
	
	//질문하기, 답하기,리스트보기 전부다 여기꺼 사용
	//11
	
}
