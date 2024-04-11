package com.geomhwein.go.securlty.service;

import com.geomhwein.go.command.UserAuthVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NormalUserMapper {

	public int signUp(UserAuthVO UserAuthVO);
	public UserAuthVO signIn(String userId);

}
