package com.geomhwein.go.creator.service;

import org.apache.ibatis.annotations.Mapper;

import com.geomhwein.go.command.HomeworkVO;
import com.geomhwein.go.command.EducationGroupVO;

@Mapper
public interface CreatorMapper {

	public void makeHomework(HomeworkVO vo);
	

	public int getApplyCount();

	public EducationGroupVO getApply(int i);

}
