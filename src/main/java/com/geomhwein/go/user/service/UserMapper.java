package com.geomhwein.go.user.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.geomhwein.go.command.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.RequestParam;
import com.geomhwein.go.command.ComunityUploadVO;
import com.geomhwein.go.command.ReplyVO;
import com.geomhwein.go.command.SubmissionVO;
import com.geomhwein.go.command.UserDetailsVO;
import com.geomhwein.go.command.ComunityVO;
import com.geomhwein.go.util.Criteria;


@Mapper
public interface UserMapper {
	
	public int comunityForm(ComunityVO vo);

	public List<ComunityVO> getComunityList(Criteria cri);
	public ComunityVO getComunityDetail(int pst_ttl_no);
	public int comunityModifyForm(ComunityVO vo);
	public int comunityDelete(int pst_ttl_no);
	public void updateHit(int pst_ttl_no);
	public int comunityTotal(Criteria cri);
	public void registFile(ComunityUploadVO vo);
	public List<ComunityUploadVO> getFile(int pst_ttl_no);
	public void replyAdd(ReplyVO vo);
	public void addQuestion(QuestionVO vo);
	public List<QuestionVO> getQuestionList(String username);
	public QuestionVO questionDetail(int qstn_no);
	public void questionModifyForm(QuestionVO vo);
	public void deleteQuestion(int qstnno);
	public void applyCancle(int aplyNo);
	public void deleteUpload(int pst_ttl_no);



	@Select("SELECT * FROM USER_DETAILS WHERE USER_ID = #{userId}")
	public UserDetailsVO getUserDetails (String userId);
	public ArrayList<EducationGroupVO> getAllEducationGroup(String userId);
	public void updateProfile(UserDetailsVO userDetailsVO);

  
	public List<ReplyVO> getReplyList(int pst_ttl_no);
	public void replyUpdate(ReplyVO vo);
	public void replyDelete(int reply_no);
	public List<ReplyVO> getChildList(int parent_reply_no);
	public void replyCount(int pst_ttl_no);
	public void deleteCount(int pst_ttl_no);
	public List<ReplyVO> replyFilter(int reply_no);
	public void replyStatus(int reply_no);
	public void allReplyDelete(int pst_ttl_no);
	public void deleteFile(int pst_ttl_no);

	
	public List<GroupApplicationVO> getGroupApplyList(String userId);
	public QuestionVO getAnswer(int qstnNo);
	public List<HomeworkVO> getHomeworkList(String userId);
	public List<EducationGroupVO> getGroup();
	public HomeworkVO homeworkReg(int asmtNo);
	public EducationGroupVO getGroup(int groupNo);
	public void submissionForm(SubmissionVO vo);
	public SubmissionVO getSubmission(@Param("userId") String userId ,@Param("asmtNo") int amstNo);
	public void submissionUpdate(SubmissionVO vo);
	

	public void applyGroup(@Param("groupNo")int groupNo,@Param("userId")String userId);

	public List<UserDetailsVO> getUserScoreList();

	public void addBasket(@Param("groupNo") int groupNo,@Param("userId") String userId);

	public void registCreator(EvaluationVO vo);

	public EducationGroupVO getGroupOne(int groupNo);

	

}
