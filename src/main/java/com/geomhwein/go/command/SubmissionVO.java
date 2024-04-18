package com.geomhwein.go.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubmissionVO {
   
   private int subNo;
   private String subCn;
   private int subScr;
   private String subYmd;
   private int asmtNo;
   private String userId;
   
}