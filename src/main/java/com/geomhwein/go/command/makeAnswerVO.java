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
public class makeAnswerVO {
	
	private String userId;
	private String ansCn;
	private Timestamp ansYmd;
	private int ansNo;
	private int qstnNo;
}
