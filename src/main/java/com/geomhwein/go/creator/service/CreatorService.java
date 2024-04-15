package com.geomhwein.go.creator.service;

import com.geomhwein.go.command.HomeworkVO;
import com.geomhwein.go.command.educationGroupVO;

public interface CreatorService {

	public void makeHomework(HomeworkVO vo);

	public int getApplyCount();

	public educationGroupVO getApply(int i);

}
