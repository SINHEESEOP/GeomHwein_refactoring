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
public class educationGroupVO {
	
	private String groupNm;
	private int groupNo;
	private String userId;
	private int groupUtztnNope;
	private Timestamp lastCmcrsYmd;
	private int contsSn;
	private int recAge;
}
