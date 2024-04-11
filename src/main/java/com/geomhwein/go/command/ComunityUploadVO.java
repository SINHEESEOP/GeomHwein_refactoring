package com.geomhwein.go.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ComunityUploadVO {

	private Integer upload_no;
	private String filename;
	private String filepath;
	private String uuid;
	private String regdate;
	private Integer pst_ttl_no;
	private String userId;
	
}
