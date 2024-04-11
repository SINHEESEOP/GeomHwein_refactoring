package com.geomhwein.go.service;

import org.apache.ibatis.annotations.Mapper;

import com.geomhwein.go.command.AdminVO;

@Mapper
public interface AdminMapper {

	public int mttrInsert  (AdminVO vo);
}
