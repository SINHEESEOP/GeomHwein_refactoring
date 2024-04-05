package com.geomhwein.go.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geomhwein.go.command.comunityVO;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public int comunityForm(comunityVO vo) {
		
		
		return userMapper.comunityForm(vo);
	}
}
