package com.geomhwein.go.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContentUploadVO {
	
	private Integer uploadNo;
	private String fileName;
	private String filePath;
	private String uuId;
	private int contsSn;
	
}
