package com.geomhwein.go.securlty.service;

import com.geomhwein.go.command.CartVO;
import com.geomhwein.go.command.UserAuthVO;
import com.geomhwein.go.command.EducationGroupVO;
import com.geomhwein.go.util.Criteria;

import java.util.ArrayList;

public interface NormalUserService {

	public int signUp(UserAuthVO UserAuthVO);
	public ArrayList<EducationGroupVO> getList(Criteria cri);
	public ArrayList<UserAuthVO> getUserList(Criteria cri, String id);
	public ArrayList<EducationGroupVO> getList2(Criteria cri);
	public ArrayList<CartVO> getCart(String userId);
	public int deleteCart(Long groupNo, String userId);
}
