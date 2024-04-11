package com.geomhwein.go.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReplyVO {

	private Integer reply_no;
	private Integer pst_ttl_no;
	private String reply_cn;
	private String username;
	
}
