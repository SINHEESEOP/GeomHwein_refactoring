package com.geomhwein.go.securlty.service;

import com.geomhwein.go.command.EducationGroupVO;
import com.geomhwein.go.command.UserAuthVO;
import com.geomhwein.go.util.Criteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface NormalUserMapper {

	public int signUp(UserAuthVO UserAuthVO);
	public UserAuthVO signIn(String userId);
	public ArrayList<EducationGroupVO> getList(Criteria cri);
	public ArrayList<UserAuthVO> getUserList(Criteria cri, String id);
}
