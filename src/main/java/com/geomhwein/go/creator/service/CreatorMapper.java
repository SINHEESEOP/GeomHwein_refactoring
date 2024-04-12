package com.geomhwein.go.creator.service;

import org.apache.ibatis.annotations.Mapper;

import com.geomhwein.go.command.HomeworkVO;
import com.geomhwein.go.command.educationGroupVO;

@Mapper
public interface CreatorMapper {

	public int makeHomework(HomeworkVO vo);
	//mapper작업 아직 없음

	public int getApplyCount();

	public educationGroupVO getApply(int i);

}
