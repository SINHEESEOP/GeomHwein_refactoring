package com.geomhwein.go.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geomhwein.go.command.HomeworkVO;
import com.geomhwein.go.command.QuestionVO;
import com.geomhwein.go.command.comunityVO;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public int comunityForm(comunityVO vo) {
		
		
		return userMapper.comunityForm(vo);
	}

	
	public void addQuestion(QuestionVO vo) {
		
		userMapper.addQuestion(vo);
	}


	
	public int registCreator(String userName, String docsCode, String reason) {
		
		return userMapper.registCreator(userName,docsCode,reason);
	}


	
	public List<HomeworkVO> getHomeworkList() {
		
		return userMapper.getHomeworkList();
	}
}
