package com.geomhwein.go.creator.service;

import com.geomhwein.go.command.HomeworkVO;
import com.geomhwein.go.command.QuestionVO;
import com.geomhwein.go.command.SubmissionVO;
import com.geomhwein.go.command.UserDetailsVO;

import java.util.List;

import com.geomhwein.go.command.ContentVO;
import com.geomhwein.go.command.EducationGroupVO;

public interface CreatorService {
	
	
	//숙제등록
	public void makeHomework(HomeworkVO vo);
	
	//그룹신청자수 조회
	public int getApplyCount();
	
	//그룹신청 조회
	public List<EducationGroupVO> getApply();
	
	//숙제조회
	public List<SubmissionVO> getHomeworkDone(String userId);
	
	//모든 학생 조회
	public List<UserDetailsVO> getAllStudent();
	
	//신청반려
	public void deleteApply(int aplyNo);
	
	//질문조회
	public List<QuestionVO> getQuestionList(String userId);
	
	//답변등록-질문조회
	public QuestionVO getQuestion(int qstnNo);
	
	//답변등록-등록
	public void addAnswer(QuestionVO vo);

	//숙제상제보기
	public SubmissionVO getSubmission(int subNo);
	
	//정답처리 로직
	//현재 유저 점수 조회
	public int getUserScore(String userId);
	//현재점수+숙제점수 = 유저점수 갱신
	public void setUserScore(String userId, int newScore);
	
	//제출된 숙제 점수 갱신- 디폴트 0
	public void setSubmissionScore(int subScr, int subNo);
	
	//그룹승인시 상태변경
	public void setApplyStatus(int aplyNo);
	//그룹신청승인시 신청가능인원 변경
	public void setGroupUtztnNope(int groupNo);
	
	//질의응답 상태재설정
	public void setQstnStatus(QuestionVO vo);
	
	
	//크리에이터 메인
	public List<ContentVO> getAllContentList();
	
	public ContentVO getContent(int contsSn);

	public void addGroup(EducationGroupVO evo);
	

	

}
