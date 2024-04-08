package com.geomhwein.go.user.service;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.geomhwein.go.command.HomeworkVO;
import com.geomhwein.go.command.QuestionVO;
import com.geomhwein.go.command.comunityVO;

@Mapper
public interface UserMapper {
	
	public int comunityForm(comunityVO vo);

	public void addQuestion(QuestionVO vo);
	//아직 mapper작업 안햇음

	public int registCreator(String userName, String docsCode, String reason);
	//아직 mapper작업 안함
	//반환값으로 성공실패여부 확인

	public List<HomeworkVO> getHomeworkList();
	//mapper작업 안함
	//리스트 받아와서 Homeworklist창으로 가서 타임리프 돌려서 화면에 리스트 뿌려줌
	
}
