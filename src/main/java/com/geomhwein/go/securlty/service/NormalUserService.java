package com.geomhwein.go.securlty.service;

import com.geomhwein.go.command.UserAuthVO;
import com.geomhwein.go.command.EducationGroupVO;
import com.geomhwein.go.util.Criteria;

import java.util.ArrayList;
import java.util.List;

public interface NormalUserService {

	public int signUp(UserAuthVO UserAuthVO);
	public ArrayList<EducationGroupVO> getList(Criteria cri);
	public ArrayList<UserAuthVO> getUserList(Criteria cri, String id);
	public ArrayList<EducationGroupVO> getList2(Criteria cri);
	public ArrayList<EducationGroupVO> getCart(String userId);

}
