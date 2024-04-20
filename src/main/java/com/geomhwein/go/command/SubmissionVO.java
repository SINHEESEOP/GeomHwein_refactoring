package com.geomhwein.go.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubmissionVO {
	
	private Integer subNo;
	private String subCn;
	private Integer subScr;
	private String subYmd;
	private Integer asmtNo;
	private String userId;
	
}
