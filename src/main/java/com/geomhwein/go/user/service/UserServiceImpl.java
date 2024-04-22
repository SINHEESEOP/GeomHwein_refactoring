package com.geomhwein.go.user.service;


import java.io.File;
import java.security.Principal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;

import com.geomhwein.go.command.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import com.geomhwein.go.command.ComunityUploadVO;
import com.geomhwein.go.command.ReplyVO;
import com.geomhwein.go.command.SubmissionVO;
import com.geomhwein.go.command.UserDetailsVO;
import com.geomhwein.go.command.ComunityVO;
import com.geomhwein.go.util.Criteria;

import com.geomhwein.go.command.EducationGroupVO;
import com.geomhwein.go.command.GroupApplicationVO;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;
	
	@Value("${project.upload.path}")
	private String uploadPath;
	
	//날짜폴더 만드는 함수
	public String makeFolder() {
			
		String filepath = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
		
		File file = new File(uploadPath + "/" + filepath);
		
		if(file.exists() == false) { //해당 파일이 있으면 true, 없으면 false
			
			file.mkdirs();
		
		}
		return filepath;
	}

	public UserDetailsVO getUserDetails (String userId) {
		return userMapper.getUserDetails(userId);
	}

	@Override
	public ArrayList<EducationGroupVO> getAllEducationGroup(String userId) {

		ArrayList<EducationGroupVO> userEduList = userMapper.getAllEducationGroup(userId);

		for (int i = 0; i < userEduList.size(); i++) {
			String time = userEduList.get(i).getContentVO().getUtztnBgngYmd().substring(0, 10);
			userEduList.get(i).getContentVO().setUtztnBgngYmd(time);
		}
		return userEduList;
	}

	@Transactional
	public void updateProfile(UserDetailsVO userDetailsVO) {
		userMapper.updateProfile(userDetailsVO);
	}

	@Override
	@Transactional(rollbackFor = Exception.class) //에러시 롤백처리
	public int comunityForm(ComunityVO vo , List<MultipartFile> list, String userId) {
		
		int result = userMapper.comunityForm(vo);
		
		//업로드작업처리
		for(MultipartFile file :list) {
			 //파일명 //브라우저별로 파일 경로가 포함되서 올라오는 경우가 있음
			String filename = file.getOriginalFilename();
			filename = filename.substring(filename.lastIndexOf("\\") + 1);
			
			//동일한 파일로 업로드 시 , 기존 파일이 덮어지기 때문에 랜덤한 이름을 이용해서 파일명칭 변경
			String uuid = UUID.randomUUID().toString();
			//날짜별로 폴더생성
			String filepath = makeFolder();
			//업로드경로
			String savePath = uploadPath + "/" + filepath + "/" + uuid + "_" + filename;
			
			System.out.println(filename); //원본파일명 DB저장
			System.out.println(filepath); //폴더명 DB저장
			System.out.println(uuid); //랜덤한 이름 DB저장
			System.out.println(savePath); //업로드경로
			
			try {
				File saveFile = new File(savePath);
				file.transferTo(saveFile); //업로드
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			//업로드 이후에는 데이터베이스에 경로를 저장
			userMapper.registFile(ComunityUploadVO.builder()
								.fileName(filename)
								.filePath(filepath)
								.uuid(uuid)
								.userId(userId).build());
		}
		
		return result;
	}

	@Override
	public List<ComunityVO> getComunityList(Criteria cri) {
		
		
		return userMapper.getComunityList(cri);
	}

	@Override
	public ComunityVO getComunityDetail(int pst_ttl_no) {
		
		return userMapper.getComunityDetail(pst_ttl_no);
	}

	@Override
	public int comunityModifyForm(ComunityVO vo , List<MultipartFile> list , String userId) {
		
		userMapper.deleteFile(vo.getPstTtlNo());
		
	
		for(MultipartFile file :list) {
			 //파일명 //브라우저별로 파일 경로가 포함되서 올라오는 경우가 있음
			String filename = file.getOriginalFilename();
			filename = filename.substring(filename.lastIndexOf("\\") + 1);
			
			//동일한 파일로 업로드 시 , 기존 파일이 덮어지기 때문에 랜덤한 이름을 이용해서 파일명칭 변경
			String uuid = UUID.randomUUID().toString();
			//날짜별로 폴더생성
			String filepath = makeFolder();
			//업로드경로
			String savePath = uploadPath + "/" + filepath + "/" + uuid + "_" + filename;
			
			System.out.println(filename); //원본파일명 DB저장
			System.out.println(filepath); //폴더명 DB저장
			System.out.println(uuid); //랜덤한 이름 DB저장
			System.out.println(savePath); //업로드경로
			
			try {
				File saveFile = new File(savePath);
				file.transferTo(saveFile); //업로드
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			//업로드 이후에는 데이터베이스에 경로를 저장
			userMapper.registFile(ComunityUploadVO.builder()
								.fileName(filename)
								.filePath(filepath)
								.uuid(uuid)
								.userId(userId).build());
		}
		
		return userMapper.comunityModifyForm(vo);
	}

	@Override
	public int comunityDelete(int pst_ttl_no) {
		// TODO Auto-generated method stub
		return userMapper.comunityDelete(pst_ttl_no);
	}

	@Override
	public void updateHit(int pst_ttl_no) {
		// TODO Auto-generated method stub
		userMapper.updateHit(pst_ttl_no);
		
	}


	@Override
	public int comunityTotal(Criteria cri) {
		
		return userMapper.comunityTotal(cri);
	}

	@Override
	public List<ComunityUploadVO> getFile(int pst_ttl_no) {
	
		return userMapper.getFile(pst_ttl_no);
	}


	@Override
	public void replyAdd(ReplyVO vo) {
		
		userMapper.replyAdd(vo);
		
	}



	
	public void addQuestion(QuestionVO vo) {
		
		userMapper.addQuestion(vo);
	}


	
	public List<HomeworkVO> getHomeworkList(String userId) {
		
		return userMapper.getHomeworkList(userId);
	}


	
	public List<EducationGroupVO> getGroup() {
		
		return userMapper.getGroup();
	}

	


	@Override
	public void applyGroup(int groupNo, String username) {
		
		userMapper.applyGroup(groupNo,username);
		
	}

	@Override
	public List<QuestionVO> getQuestionList(String username) {
		
		return userMapper.getQuestionList(username);
	}


	@Override
	public QuestionVO questionDetail(int qstn_no) {
		
		return userMapper.questionDetail(qstn_no);
	}


	@Override
	public void questionModifyForm(QuestionVO vo) {
		
		userMapper.questionModifyForm(vo);
	}


	@Override
	public void deleteQuestion(int qstnno) {

		userMapper.deleteQuestion(qstnno);
		
	}
	
	@Override
	public List<ReplyVO> getReplyList(int pst_ttl_no) {
		
		return userMapper.getReplyList(pst_ttl_no);
	}


	@Override
	public void replyUpdate(ReplyVO vo) {
		
		userMapper.replyUpdate(vo);
		
	}


	@Override
	public void replyDelete(int reply_no) {
		
		userMapper.replyDelete(reply_no);
	}


	@Override
	public List<ReplyVO> getChildList(int parent_reply_no) {
		
		return userMapper.getChildList(parent_reply_no);
	}


	@Override
	public void replyCount(int pst_ttl_no) {
		
		userMapper.replyCount(pst_ttl_no);	
	}


	@Override
	public void deleteCount(int pst_ttl_no) {
		
		userMapper.deleteCount(pst_ttl_no);
		
	}

	@Override
	public List<ReplyVO>replyFilter(int reply_no) {
		
		return userMapper.replyFilter(reply_no);
	}


	@Override
	public void replyStatus(int reply_no) {
		
		userMapper.replyStatus(reply_no);
	}


	@Override
	public void allReplyDelete(int pst_ttl_no) {
		
		userMapper.allReplyDelete(pst_ttl_no);
		
	}


	@Override
	public List<GroupApplicationVO> getGroupApplyList(String userId) {
		
		return userMapper.getGroupApplyList(userId);
	}

	@Override
	public QuestionVO getAnswer(int qstnNo) {
		
		return userMapper.getAnswer(qstnNo);
	}


	@Override
	public HomeworkVO homeworkReg(int asmtNo) {
		
		return userMapper.homeworkReg(asmtNo);
	}


	@Override
	public void submissionForm(SubmissionVO vo) {
		
		userMapper.submissionForm(vo);
	}


	@Override
	public SubmissionVO getSubmission(String userId, int amstNo) {
		
		return userMapper.getSubmission(userId, amstNo);
	}


	@Override
	public void submissionUpdate(SubmissionVO vo) {

		userMapper.submissionUpdate(vo);
	}
		
	//사활풀이 순위
	public List<UserDetailsVO> getUserScoreList() {

		return userMapper.getUserScoreList();
	}

	//장바구니 담기
	public void addBasket (int groupNo, String userId) {
		userMapper.addBasket(groupNo,userId);
	}

	@Override
	public void registCreator (EvaluationVO vo) {
		userMapper.registCreator(vo);
	}

	@Override
	public EducationGroupVO getGroupOne(int groupNo) {
		
		return userMapper.getGroupOne(groupNo);
	}

	@Override
	public void applyCancle(int aplyNo) {
		
		userMapper.applyCancle(aplyNo);
	}

	@Override
	public void deleteFile(int pst_ttl_no) {
		
		userMapper.deleteFile(pst_ttl_no);
		
	}

}
