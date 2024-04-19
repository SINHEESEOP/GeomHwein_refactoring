package com.geomhwein.go.creator.service;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.geomhwein.go.command.HomeworkVO;
import com.geomhwein.go.command.QuestionVO;
import com.geomhwein.go.command.SubmissionVO;
import com.geomhwein.go.command.UserDetailsVO;
import com.geomhwein.go.command.ContentVO;
import com.geomhwein.go.command.EducationGroupVO;

@Mapper
public interface CreatorMapper {

	public void makeHomework(HomeworkVO vo);
	

	public int getApplyCount();

	public List<EducationGroupVO> getApply();


	public List<SubmissionVO> getHomeworkDone(String userId);


	public void setUserScore(@Param("userId")String userId,@Param("newScore") int newScore);


	public int getUserScore(String userId);


	


	public List<UserDetailsVO> getAllStudent();


	public void deleteApply(int aplyNo);


	public List<QuestionVO> getQuestionList(String userId);


	public QuestionVO getQuestion(int qstnNo);


	public void addAnswer(QuestionVO vo);


	public SubmissionVO getSubmission(int subNo);


	public void setSubmissionScore(@Param("subScr")int subScr,@Param("subNo") int subNo);


	public void setApplyStatus(int aplyNo);


	public void setGroupUtztnNope(int groupNo);


	public void setQstnStatus(QuestionVO vo);


	public List<ContentVO> getAllContentList();


	public ContentVO getContent(int contsSn);


	public void addGroup(EducationGroupVO evo);


	

}
