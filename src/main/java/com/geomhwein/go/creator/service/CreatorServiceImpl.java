package com.geomhwein.go.creator.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geomhwein.go.command.HomeworkVO;
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

	
	public List<HomeworkVO> getHomeworkDone(String userId) {
		
		return creatorMapper.getHomeworkDone(userId);
	}

	
	

	
	
}
