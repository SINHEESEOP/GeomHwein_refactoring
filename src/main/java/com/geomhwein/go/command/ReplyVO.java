package com.geomhwein.go.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReplyVO {

	private Integer replyNo;
	private Integer pstTtlNo;
	private String replyCn;
	private String userId;
	private Integer parentReplyNo;
	private Boolean replyStatus;
	private String replyReg;
}
