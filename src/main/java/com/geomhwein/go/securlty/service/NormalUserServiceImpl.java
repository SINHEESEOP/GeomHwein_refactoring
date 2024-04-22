package com.geomhwein.go.securlty.service;

import com.geomhwein.go.command.CartVO;
import com.geomhwein.go.command.UserAuthVO;
import com.geomhwein.go.command.EducationGroupVO;
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
	public ArrayList<EducationGroupVO> getList(Criteria cri) {
		return normalUserMapper.getList(cri);
	}

	public ArrayList<UserAuthVO> getUserList(Criteria cri, String id) {
		return normalUserMapper.getUserList(cri, id);
	}

	@Override
	public ArrayList<EducationGroupVO> getList2(Criteria cri) {
		return normalUserMapper.getList2(cri);
	}

	@Override
	public ArrayList<CartVO> getCart(String userId) {
		return normalUserMapper.getCart(userId);
	}

	public int deleteCart(Long groupNo, String userId) {
		return normalUserMapper.deleteCart(groupNo, userId);
	}

}
