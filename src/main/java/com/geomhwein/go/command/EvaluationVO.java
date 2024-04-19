package com.geomhwein.go.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EvaluationVO {

	private String userId;
	private String docId;
	private String docType;
	private String documentImageUrl;
	private String relatedArticleUrl;
	private String regDt;
	
}
