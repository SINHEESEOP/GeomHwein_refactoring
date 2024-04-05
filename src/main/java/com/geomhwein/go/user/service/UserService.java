package com.geomhwein.go.user.service;

import com.geomhwein.go.command.QuestionVO;
import com.geomhwein.go.command.comunityVO;

public interface UserService {

	public int comunityForm(comunityVO vo);

	public void addQuestion(QuestionVO vo);

	
}
