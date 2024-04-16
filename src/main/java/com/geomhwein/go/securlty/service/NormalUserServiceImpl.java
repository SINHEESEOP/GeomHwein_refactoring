package com.geomhwein.go.securlty.service;

import com.geomhwein.go.command.UserAuthVO;
import com.geomhwein.go.command.educationGroupVO;
import com.geomhwein.go.util.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service("normalUserService")
public class NormalUserServiceImpl implements NormalUserService{

	@Autowired
	NormalUserMapper normalUserMapper;


	@Override
	public int signUp(UserAuthVO UserAuthVO) {
		return normalUserMapper.signUp(UserAuthVO);
	}

	@Override
	public ArrayList<educationGroupVO> getList(Criteria cri) {
		return normalUserMapper.getList(cri);
	}

	public ArrayList<UserAuthVO> getUserList(Criteria cri, String id) {
		return normalUserMapper.getUserList(cri, id);
	}

}
