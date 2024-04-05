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
	
	private String userId;
	private String ansCn;
	private String ansYmd;
	private int ansNo;
	private int qstnNo;
	private String qusCn;
	private String qusYmd;
	//질문하기, 답하기,리스트보기 전부다 여기꺼 사용
	
}
