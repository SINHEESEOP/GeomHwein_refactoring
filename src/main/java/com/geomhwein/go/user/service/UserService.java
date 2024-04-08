package com.geomhwein.go.user.service;

import java.util.List;

import com.geomhwein.go.command.HomeworkVO;
import com.geomhwein.go.command.QuestionVO;
import com.geomhwein.go.command.comunityVO;

public interface UserService {

	public int comunityForm(comunityVO vo);

	public void addQuestion(QuestionVO vo);

	public int registCreator(String userName, String docsCode, String reason);

	public List<HomeworkVO> getHomeworkList(String userId);

	
}
