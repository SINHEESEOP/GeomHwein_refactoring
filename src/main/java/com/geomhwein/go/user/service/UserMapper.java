package com.geomhwein.go.user.service;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.geomhwein.go.command.comunityVO;
import com.geomhwein.go.util.Criteria;

@Mapper
public interface UserMapper {
	
	public int comunityForm(comunityVO vo);
	public List<comunityVO> getComunityList(Criteria cri);
	public comunityVO getComunityDetail(int pst_ttl_no);
	public int comunityModifyForm(comunityVO vo);
	public int comunityDelete(int pst_ttl_no);
	public void updateHit(int pst_ttl_no);
	public int comunityTotal(Criteria cri);
}
