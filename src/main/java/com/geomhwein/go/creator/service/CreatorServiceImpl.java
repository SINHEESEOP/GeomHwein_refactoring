package com.geomhwein.go.creator.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geomhwein.go.command.HomeworkVO;
import com.geomhwein.go.command.QuestionVO;
import com.geomhwein.go.command.SubmissionVO;
import com.geomhwein.go.command.UserDetailsVO;
import com.geomhwein.go.command.ContentVO;
import com.geomhwein.go.command.EducationGroupVO;

@Service("creatorService")
public class CreatorServiceImpl implements CreatorService{
	
	@Autowired
	private CreatorMapper creatorMapper;

	//숙제등록
	public void makeHomework(HomeworkVO vo) {
		
		creatorMapper.makeHomework(vo);
		
	}

	//등록숫자
	public int getApplyCount() {
		
		return creatorMapper.getApplyCount();
	}

	//등록리스트받기
	public List<EducationGroupVO> getApply() {
		
		return creatorMapper.getApply();
	}

	//숙제제출리스트받기	
	public List<SubmissionVO> getHomeworkDone(String userId) {
		
		return creatorMapper.getHomeworkDone(userId);
	}

	//유저 점수 재설정
	public void setUserScore(String userId, int newScore) {
		
		creatorMapper.setUserScore(userId,newScore);
		
	}

	//현재 유저점수 조회
	public int getUserScore(String userId) {
		
		return creatorMapper.getUserScore(userId);
	}

	
	//모든학생조회
	public List<UserDetailsVO> getAllStudent() {
		
		return creatorMapper.getAllStudent();
	}

	//신청거절
	public void deleteApply(int aplyNo) {
		
		creatorMapper.deleteApply(aplyNo);
		
	}

	//질문리스트 받기
	public List<QuestionVO> getQuestionList(String userId) {
		
		return creatorMapper.getQuestionList(userId);
	}

	//질문에대한 답변작성로직 1
	public QuestionVO getQuestion(int qstnNo) {
		
		return creatorMapper.getQuestion(qstnNo);
	}

	//답변작성
	public void addAnswer(QuestionVO vo) {
		
		creatorMapper.addAnswer(vo);
		
	}

	//숙제 제출에 대한 값 조회
	public SubmissionVO getSubmission(int subNo) {
		
		return creatorMapper.getSubmission(subNo);
	}

	//숙제 점수 재설정
	public void setSubmissionScore(int subScr, int subNo) {
		
		creatorMapper.setSubmissionScore(subScr,subNo);
		
	}

	//가입승인절차 1
	public void setApplyStatus(int aplyNo) {
		
		creatorMapper.setApplyStatus(aplyNo);
		
	}
	
	//가입승인후 그룹 총 가용인원 재설정
	public void setGroupUtztnNope(int groupNo) {
		
		creatorMapper.setGroupUtztnNope(groupNo);
		
	}

	
	public void setQstnStatus(QuestionVO vo) {
		
		creatorMapper.setQstnStatus(vo);
		
	}

	//크리에이터 메인으로 발송
	public List<ContentVO> getAllContentList() {
		
		return creatorMapper.getAllContentList();
	}

	//컨텐츠 불러오기
	public ContentVO getContent(int contsSn) {
		
		return creatorMapper.getContent(contsSn);
	}

	//그룹등록
	public void addGroup(EducationGroupVO evo) {
		
		creatorMapper.addGroup(evo);
		
	}

	
	

	
	
}
