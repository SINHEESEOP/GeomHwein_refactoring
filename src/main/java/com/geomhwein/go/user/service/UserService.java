package com.geomhwein.go.user.service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;


import com.geomhwein.go.command.*;
import org.springframework.web.multipart.MultipartFile;
import com.geomhwein.go.util.Criteria;
import com.geomhwein.go.command.ComunityUploadVO;
import com.geomhwein.go.command.ReplyVO;
import com.geomhwein.go.command.SubmissionVO;
import com.geomhwein.go.command.UserDetailsVO;
import com.geomhwein.go.command.ComunityVO;
import com.geomhwein.go.command.HomeworkVO;
import com.geomhwein.go.command.QuestionVO;

import com.geomhwein.go.command.EducationGroupVO;
import com.geomhwein.go.command.EvaluationVO;
import com.geomhwein.go.command.GroupApplicationVO;


public interface UserService {

	public int comunityForm(ComunityVO vo , List<MultipartFile> list , String userId);
	public List<ComunityVO> getComunityList(Criteria cri);
	public ComunityVO getComunityDetail(int pst_ttl_no);
	public int comunityModifyForm(ComunityVO vo , List<MultipartFile> list , String userId);
	public int comunityDelete(int pst_ttl_no);
	public void updateHit(int pst_ttl_no);
	public int comunityTotal(Criteria cri);
	public List<ComunityUploadVO> getFile(int pst_ttl_no);
	public void replyAdd(ReplyVO vo);
	public List<HomeworkVO> getHomeworkList(String userId);
	public void addQuestion(QuestionVO vo);
	
	public void applyGroup(int groupNo, String username);
	public List<QuestionVO> getQuestionList(String username);
	public QuestionVO questionDetail(int qstn_no);
	public void questionModifyForm(QuestionVO vo);
	public void deleteQuestion(int qstnno);


	public UserDetailsVO getUserDetails (String userId);
	public ArrayList<EducationGroupVO> getAllEducationGroup(String userId);
	public void updateProfile(UserDetailsVO userDetailsVO);

	public List<ReplyVO> getReplyList(int pst_ttl_no);
	public void replyUpdate(ReplyVO vo);
	public void replyDelete(int reply_no);
	public List<ReplyVO> getChildList(int parent_reply_no);
	public void replyCount(int pst_ttl_no);
	public void deleteCount(int pst_ttl_no);
	public  List<ReplyVO> replyFilter(int reply_no);
	public void replyStatus(int reply_no);
	public void allReplyDelete(int pst_ttl_no);
	public List<GroupApplicationVO> getGroupApplyList(String userId);
	public QuestionVO getAnswer(int qstnNo);
	public HomeworkVO homeworkReg(int asmtNo);
	public void submissionForm(SubmissionVO vo);
	public SubmissionVO getSubmission(String userId , int amstNo);
	public void submissionUpdate(SubmissionVO vo);
	public List<UserDetailsVO> getUserScoreList();
	public void addBasket(int groupNo, String userId);
	public void registCreator(EvaluationVO vo);
	public EducationGroupVO getGroupOne(int groupNo);
	public List<EducationGroupVO> getGroup();
	public void applyCancle(int aplyNo);
	public void deleteFile(int pst_ttl_no);

}
