package com.geomhwein.go.command;


import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminVO {

	private  Integer mttrSn;
	private String mttrTtl;
	private LocalDate bgngTm;
	private String mttrCn;
	private String userId;
	
	
	
}
