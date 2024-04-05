package com.geomhwein.go.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geomhwein.go.command.AdminVO;

@Service("AdminService")
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	private AdminMapper adminMapper;
	

	@Override
	public int mttrInsert(AdminVO vo) {
		
		return adminMapper.mttrInsert(vo);
	}

	
}
