package com.geomhwein.go.user.service;

import org.apache.ibatis.annotations.Mapper;

import com.geomhwein.go.command.QuestionVO;
import com.geomhwein.go.command.comunityVO;

@Mapper
public interface UserMapper {
	
	public int comunityForm(comunityVO vo);

	public void addQuestion(QuestionVO vo);
	//아직 mapper작업 안햇음
}
