package com.geomhwein.go.user.service;


import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


import com.geomhwein.go.command.ComunityUploadVO;
import com.geomhwein.go.command.ReplyVO;
import com.geomhwein.go.command.ComunityVO;
import com.geomhwein.go.util.Criteria;

import com.geomhwein.go.command.HomeworkVO;
import com.geomhwein.go.command.QuestionVO;
import com.geomhwein.go.command.ComunityVO;
import com.geomhwein.go.command.educationGroupVO;

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
	

	@Override
	@Transactional(rollbackFor = Exception.class) //에러시 롤백처리
	public int comunityForm(ComunityVO vo , List<MultipartFile> list) {
		
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
								.filename(filename)
								.filepath(filepath)
								.uuid(uuid)
								.userId("aaa123").build());
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
	public int comunityModifyForm(ComunityVO vo) {
		
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


	
	public int registCreator(String userName, String docsCode, String reason) {
		
		return userMapper.registCreator(userName,docsCode,reason);
	}


	
	public List<HomeworkVO> getHomeworkList(String userId) {
		
		return userMapper.getHomeworkList(userId);
	}


	@Override
	public educationGroupVO getGroup(int groupNo) {
		
		return userMapper.getGroup(groupNo);
	}

}
