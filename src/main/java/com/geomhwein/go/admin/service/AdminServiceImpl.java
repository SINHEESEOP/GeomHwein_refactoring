package com.geomhwein.go.admin.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geomhwein.go.command.AdminVO;
import com.geomhwein.go.command.ContentVO;
import com.geomhwein.go.command.UserDetailsVO;

@Service("AdminService")
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	private AdminMapper adminMapper;
	

	@Override
	public int mttrInsert(AdminVO vo) {
		
		return adminMapper.mttrInsert(vo);
	}


	@Override
	public ArrayList<AdminVO> mttrList() {
		
		return adminMapper.mttrList();
	}


	@Override
	public AdminVO mttrDetail(int mttrSn) {
		
		return adminMapper.mttrDetail(mttrSn);
	}


	@Override
	public void deleteMttr(int mttrSn) {
		
		adminMapper.deleteMttr(mttrSn);
		
		
	}


	@Override
	public ArrayList<UserDetailsVO> AllUserList() {
		
		return adminMapper.AllUserList();
	}


	@Override
	public ArrayList<ContentVO> ContentList() {
		
		
		return adminMapper.ContentList();
	}












	
}
