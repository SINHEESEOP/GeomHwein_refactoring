package com.geomhwein.go.creator.service;

import org.apache.ibatis.annotations.Mapper;

import com.geomhwein.go.command.HomeworkVO;

@Mapper
public interface CreatorMapper {

	void makeHomework(HomeworkVO vo);
	//mapper작업 아직 없음

}
