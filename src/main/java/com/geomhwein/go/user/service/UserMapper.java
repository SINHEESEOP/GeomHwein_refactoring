package com.geomhwein.go.user.service;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.geomhwein.go.command.comunityVO;

@Mapper
public interface UserMapper {
	
	public int comunityForm(comunityVO vo);
	public List<comunityVO> getComunityList();
	public comunityVO getComunityDetail(int pst_ttl_no);
	public int comunityModifyForm(comunityVO vo);
}
