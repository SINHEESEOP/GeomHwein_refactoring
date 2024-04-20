package com.geomhwein.go.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContentVO {

	private BigInteger contsSn;
	private String contsNm;
	private String contsExpln;
	private Number contsPrc;
	private LocalDateTime contsYmd;
	private String imgNm;
	private LocalDateTime utztnBgngYmd;
	private String utztnTrmsCn;
	private String utztnNope;
	private String contsGrd;
	private String userId;
	private String keywords;
	private String fileName;
	private String filePath;
	private String uuId;

	public ContentVO(String userId, String keywords) {
		this.userId = userId;
		this.keywords = keywords;
	}
	
	
}
