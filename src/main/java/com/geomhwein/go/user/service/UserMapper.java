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
import com.geomhwein.go.command.UserDetailsVO;
import com.geomhwein.go.command.ComunityVO;
import com.geomhwein.go.util.Criteria;

import com.geomhwein.go.command.ComunityVO;
import com.geomhwein.go.command.EducationGroupVO;
import com.geomhwein.go.command.GroupApplicationVO;


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


	@Select("SELECT * FROM USER_DETAILS WHERE USER_ID = #{userId}")
	public UserDetailsVO getUserDetails (String userId);
	public ArrayList<UserDetailsVO> getAllEducationGroup(String userId);
//	public ArrayList< Map<String, Object>> getAllEducationGroup(String userId);
  
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

	public int registCreator(@Param("userName") String userName,@Param("docsCode") String docsCode,@Param("reason") String reason);
	public List<GroupApplicationVO> getGroupApplyList(String userId);
	//아직 mapper작업 안함
	//반환값으로 성공실패여부 확인

	public List<HomeworkVO> getHomeworkList(String userId);
	//mapper작업 안함
	//리스트 받아와서 Homeworklist창으로 가서 타임리프 돌려서 화면에 리스트 뿌려줌

	public EducationGroupVO getGroup(int groupNo);
	//mapper상에서 
	//group테이블에서 groupNo값으로 불러와야함

	public int getGroupCount();

	public void applyGroup(@Param("groupNo")int groupNo,@Param("userId")String userId);

	public List<UserDetailsVO> getUserScoreList();

	

}
