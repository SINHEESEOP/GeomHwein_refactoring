package com.geomhwein.go.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContentVO {

	private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	private UserDetailsVO userDetailsVO;
	private BigInteger contsSn;
	private String contsNm;
	private String contsExpln;
	private int contsPrc;
	private String contsYmd;
	private String imgNm;
	private String utztnBgngYmd;
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
