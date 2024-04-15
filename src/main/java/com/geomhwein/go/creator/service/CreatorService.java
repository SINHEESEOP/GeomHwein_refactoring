package com.geomhwein.go.creator.service;

import com.geomhwein.go.command.HomeworkVO;
import com.geomhwein.go.command.EducationGroupVO;

public interface CreatorService {

	public void makeHomework(HomeworkVO vo);

	public int getApplyCount();

	public EducationGroupVO getApply(int i);

	public int getHomeworkDone(String userId);

	public HomeworkVO getHomeworkDoneVO(int i);

}
