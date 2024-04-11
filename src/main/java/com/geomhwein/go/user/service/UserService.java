package com.geomhwein.go.user.service;

import java.util.List;


import org.springframework.web.multipart.MultipartFile;

import com.geomhwein.go.command.ComunityUploadVO;
import com.geomhwein.go.command.ReplyVO;
import com.geomhwein.go.command.comunityVO;
import com.geomhwein.go.util.Criteria;

public interface UserService {

	public int comunityForm(comunityVO vo , List<MultipartFile> list);
	public List<comunityVO> getComunityList(Criteria cri);
	public comunityVO getComunityDetail(int pst_ttl_no);
	public int comunityModifyForm(comunityVO vo);
	public int comunityDelete(int pst_ttl_no);
	public void updateHit(int pst_ttl_no);
	public int comunityTotal(Criteria cri);
	public List<ComunityUploadVO> getFile(int pst_ttl_no);
	public void replyAdd(ReplyVO vo);

import com.geomhwein.go.command.HomeworkVO;
import com.geomhwein.go.command.QuestionVO;
import com.geomhwein.go.command.comunityVO;
import com.geomhwein.go.command.educationGroupVO;

public interface UserService {

	public int comunityForm(comunityVO vo);

	public void addQuestion(QuestionVO vo);

	public int registCreator(String userName, String docsCode, String reason);

	public List<HomeworkVO> getHomeworkList(String userId);

	public educationGroupVO getGroup(int groupNo);

	
}
