package com.geomhwein.go.securlty.service;

import com.geomhwein.go.command.UserAuthVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("normalUserService")
public class NormalUserServiceImpl implements NormalUserService{

	@Autowired
	NormalUserMapper normalUserMapper;


	@Override
	public int signUp(UserAuthVO UserAuthVO) {
		return normalUserMapper.signUp(UserAuthVO);
	}
}
