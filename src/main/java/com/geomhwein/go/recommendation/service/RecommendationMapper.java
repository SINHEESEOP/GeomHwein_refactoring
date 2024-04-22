package com.geomhwein.go.recommendation.service;

import com.geomhwein.go.command.ContentVO;
import com.geomhwein.go.command.UserDetailsVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RecommendationMapper {

	@Select("SELECT * FROM CONTENT")
	List<ContentVO> getAllContents();

	@Select("SELECT * FROM USER_DETAILS")
	List<UserDetailsVO> getAllUserDetails();

}
