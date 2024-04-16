package com.geomhwein.go.creator.service;

import com.geomhwein.go.command.HomeworkVO;

import java.util.List;

import com.geomhwein.go.command.EducationGroupVO;

public interface CreatorService {

	public void makeHomework(HomeworkVO vo);

	public int getApplyCount();

	public EducationGroupVO getApply(int i);

	public List<HomeworkVO> getHomeworkDone(String userId);

	public void setUserScore(String userId, int newScore);

	public int getUserScore(String userId);

	public void deleteAns(int asmtAnsNo);

	

}
