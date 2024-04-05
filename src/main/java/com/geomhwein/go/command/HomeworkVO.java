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
	
	private String userId;
	private String asmtGrd;
	private String asmtNm;
	private String asmtCn;
	private int asmtNo;
	private int asmtScr;
	
}
