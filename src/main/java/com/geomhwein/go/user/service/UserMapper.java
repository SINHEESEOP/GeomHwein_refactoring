package com.geomhwein.go.user.service;

import org.apache.ibatis.annotations.Mapper;

import com.geomhwein.go.command.comunityVO;

@Mapper
public interface UserMapper {
	
	public int comunityForm(comunityVO vo);
}
