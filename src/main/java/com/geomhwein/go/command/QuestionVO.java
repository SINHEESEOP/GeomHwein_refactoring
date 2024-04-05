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
}
