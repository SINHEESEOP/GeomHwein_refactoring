package com.geomhwein.go.recommendation.service;

import com.geomhwein.go.command.ContentVO;
import com.geomhwein.go.command.UserDetailsVO;
import com.geomhwein.go.util.DataPreprocessing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecommendationService {

	@Autowired
	private RecommendationMapper recommendationMapper;
	@Autowired
	private DataPreprocessing dataPreprocessing;

	// 콘텐츠 데이터 전처리 후 반환
//	public List<ContentVO> getProcessedContents() {
//		List<ContentVO> contents = recommendationMapper.getAllContents();
//		return contents.stream()
//				.map(content -> new ContentVO(content.getUserId(), dataPreprocessing.preprocessKeywords(content.getKeywords())))
//				.collect(Collectors.toList());
//	}

	// 사용자 관심사 데이터 전처리 후 반환
//	public List<UserDetailsVO> getProcessedUserInterests() {
//		List<UserDetailsVO> userDetails = recommendationMapper.getAllUserDetails();
//		return userDetails.stream()
//				.map(user -> new UserDetailsVO(user.getUserId(), dataPreprocessing.preprocessInterests(user.getInterests()))) // 사용자의 관심사를 전처리
//				.collect(Collectors.toList());
//	}

	public List<UserDetailsVO> getProcessedUserInterests() {
		List<UserDetailsVO> userDetails = recommendationMapper.getAllUserDetails();
		return userDetails.stream()
				.map(user -> {
					String processedInterest = dataPreprocessing.preprocessInterests(user.getInterests());
					System.out.println("Processed Interest" + processedInterest); // 로그 추가
					return new UserDetailsVO(user.getUserId(), processedInterest);
				}) // 사용자의 관심사를 전처리
				.collect(Collectors.toList());
	}

	public List<ContentVO> getProcessedContents() {
		List<ContentVO> contents = recommendationMapper.getAllContents();
		return contents.stream()
				.map(content -> {
					String processedKeywords = dataPreprocessing.preprocessKeywords(content.getKeywords());
					System.out.println("Processed Keywords: " + processedKeywords);
					return new ContentVO(content.getUserId(), processedKeywords);
				})
				.collect(Collectors.toList());
	}

}
