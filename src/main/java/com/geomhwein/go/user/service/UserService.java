package com.geomhwein.go.user.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import com.geomhwein.go.command.*;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.multipart.MultipartFile;

import com.geomhwein.go.util.Criteria;


public interface UserService {

	public int comunityForm(ComunityVO vo , List<MultipartFile> list);
	public List<ComunityVO> getComunityList(Criteria cri);
	public ComunityVO getComunityDetail(int pst_ttl_no);
	public int comunityModifyForm(ComunityVO vo);
	public int comunityDelete(int pst_ttl_no);
	public void updateHit(int pst_ttl_no);
	public int comunityTotal(Criteria cri);
	public List<ComunityUploadVO> getFile(int pst_ttl_no);
	public void replyAdd(ReplyVO vo);
	public List<HomeworkVO> getHomeworkList(String userId);
	public void addQuestion(QuestionVO vo);
	public int registCreator(String userName, String docsCode, String reason);
	public EducationGroupVO getGroup(int groupNo);
	public int getGroupCount();
	public void applyGroup(int groupNo, String username);
	public List<QuestionVO> getQuestionList(String username);
	public QuestionVO questionDetail(int qstn_no);
	public void questionModifyForm(QuestionVO vo);
	public void deleteQuestion(int qstnno);

	public UserDetailsVO getUserDetails (String userId);
	public ArrayList<UserDetailsVO> getAllEducationGroup(String userId);
//    public ArrayList< Map<String, Object> > getAllEducationGroup(String userId);
}
