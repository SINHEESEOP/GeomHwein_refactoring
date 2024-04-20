package com.geomhwein.go.admin.service;

import java.util.ArrayList;


import com.geomhwein.go.command.AdminVO;
import com.geomhwein.go.command.ContentVO;
import com.geomhwein.go.command.UserDetailsVO;

public interface AdminService {
	
	public int mttrInsert  (AdminVO vo);
	public ArrayList<AdminVO> mttrList ();
	public AdminVO mttrDetail(int mttrSn);
	public void deleteMttr(int mttrSn);
	public ArrayList<UserDetailsVO> AllUserList();
	public ArrayList<ContentVO> ContentList();
	
	
}
