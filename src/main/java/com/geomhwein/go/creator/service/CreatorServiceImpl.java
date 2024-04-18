package com.geomhwein.go.creator.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geomhwein.go.command.HomeworkVO;
import com.geomhwein.go.command.QuestionVO;
import com.geomhwein.go.command.SubmissionVO;
import com.geomhwein.go.command.UserDetailsVO;
import com.geomhwein.go.command.EducationGroupVO;

@Service("creatorService")
public class CreatorServiceImpl implements CreatorService{
	
	@Autowired
	private CreatorMapper creatorMapper;

	@Override
	public void makeHomework(HomeworkVO vo) {
		
		creatorMapper.makeHomework(vo);
		
	}

	@Override
	public int getApplyCount() {
		
		return creatorMapper.getApplyCount();
	}

	@Override
	public EducationGroupVO getApply(int i) {
		
		return creatorMapper.getApply(i);
	}

	
	public List<SubmissionVO> getHomeworkDone(String userId) {
		
		return creatorMapper.getHomeworkDone(userId);
	}

	
	public void setUserScore(String userId, int newScore) {
		
		creatorMapper.setUserScore(userId,newScore);
		
	}

	@Override
	public int getUserScore(String userId) {
		
		return creatorMapper.getUserScore(userId);
	}

	
	
	public List<UserDetailsVO> getAllStudent() {
		
		return creatorMapper.getAllStudent();
	}

	@Override
	public void deleteApply(int aplyNo) {
		
		creatorMapper.deleteApply(aplyNo);
		
	}

	@Override
	public List<QuestionVO> getQuestionList(String userId) {
		
		return creatorMapper.getQuestionList(userId);
	}

	
	public QuestionVO getQuestion(int qstnNo) {
		
		return creatorMapper.getQuestion(qstnNo);
	}

	
	public void addAnswer(QuestionVO vo) {
		
		creatorMapper.addAnswer(vo);
		
	}

	
	public SubmissionVO getSubmission(int subNo) {
		
		return creatorMapper.getSubmission(subNo);
	}

	@Override
	public void setSubmissionScore(int subScr, int subNo) {
		
		creatorMapper.setSubmissionScore(subScr,subNo);
		
	}

	
	

	
	
}
