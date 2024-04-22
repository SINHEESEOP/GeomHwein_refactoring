package com.geomhwein.go.admin.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geomhwein.go.command.AdminVO;
import com.geomhwein.go.command.ContentUploadVO;
import com.geomhwein.go.command.ContentVO;
import com.geomhwein.go.command.UserDetailsVO;
import com.geomhwein.go.util.Criteria;

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
	public ArrayList<ContentVO> ContentList(Criteria cri) {
		
		
		return adminMapper.ContentList(cri );
	}


	@Override
	public int contentInsert(ContentVO vo) {
		
		
		
		return adminMapper.contentInsert(vo);
	}


	@Override
	public ContentUploadVO getImg() {
		return adminMapper.getImg();
	}


	@Override
	public ContentVO contentDetail(int contsSn) {
		
		
		return adminMapper.contentDetail(contsSn);
	}


	@Override
	public void deleteContent(int contsSn) {
		
		
		adminMapper.deleteContent(contsSn);
	}


	@Override
	public int getContentTotal() {
		// TODO Auto-generated method stub
		return adminMapper.getContentTotal();
	}












	
}
