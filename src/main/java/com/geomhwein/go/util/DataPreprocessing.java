package com.geomhwein.go.util;

import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DataPreprocessing {

//	아래 코드는 단순화된 전처리.
//	실제 프로젝트에서는 특정 언어나 도메인에 특화된 불용어 리스트를 적용하거나,
//	자연어 처리 라이브러리를 활용하여 더 복잡한 전처리를 수행.

	// 콘텐츠 데이터 전처리 로직
	public String preprocessKeywords(String keywords) {
		if(keywords != null) {
			System.out.println("if 문 탔어요 :  " + keywords);
			return Arrays.stream(keywords.toLowerCase().split("\\P{L}+")) // 소문자 변환 후 특수 문자를 기준으로 분리
					.filter(interest -> interest.length() >= 2)
					.filter(keyword -> !keyword.trim().isEmpty()) // 공백이 아닌 키워드만 필터링
					.distinct() // 중복 제거
					.peek(keyword -> System.out.println("After distinct: " + keyword))
					.collect(Collectors.joining(",")); // 쉼표로 연결하여 하나의 문자열로 만듦
		} else {
			keywords = "";
			return Arrays.stream(keywords.toLowerCase().split("\\P{L}+")) // 소문자 변환 후 특수 문자를 기준으로 분리
					.filter(interest -> interest.length() >= 2)
					.filter(keyword -> !keyword.trim().isEmpty()) // 공백이 아닌 키워드만 필터링
					.distinct() // 중복 제거
					.peek(keyword -> System.out.println("After distinct: " + keyword))
					.collect(Collectors.joining(",")); // 쉼표로 연결하여 하나의 문자열로 만듦
		}
	}

	// 사용자 관심사 전처리 로직
	public String preprocessInterests(String interests) {
		if (interests != null) {
			System.out.println("if 문 탔어요 :  " + interests);
			return Arrays.stream(interests.toLowerCase().split("\\P{L}+")) // 소문자 변환 후 특수 문자를 기준으로 분리
					.filter(interest -> interest.length() >= 2)
					.filter(interest -> !interest.trim().isEmpty()) // 공백이 아닌 관심사만 필터링
					.distinct() // 중복 제거
					.peek(interest -> System.out.println("After distinct: " + interest))
					.collect(Collectors.joining(",")); // 쉼표로 연결하여 하나의 문자열로 만듦
		} else {
			interests = "";
			return Arrays.stream(interests.toLowerCase().split("\\W+")) // 소문자 변환 후 특수 문자를 기준으로 분리
					.filter(interest -> interest.length() >= 2)
					.filter(interest -> !interest.trim().isEmpty()) // 공백이 아닌 관심사만 필터링
					.distinct() // 중복 제거
					.peek(interest -> System.out.println("After distinct: " + interest))
					.collect(Collectors.joining(",")); // 쉼표로 연결하여 하나의 문자열로 만듦
		}
	}

	// 실제 서비스 로직에서는 해당 메서드를 사용해 DB로부터 가져온 데이터를 전처리
	// 예: contentService.getAllContents().stream().map(this::preprocessKeywords).collect(Collectors.toList());
	// 예: userService.getAllUserDetails().stream().map(this::preprocessInterests).collect(Collectors.toList());

	// 일단 보류된 메소드.
//	public String preprocessKeywords(String keywords) {
//		System.out.println("Original keywords: " + keywords);
//		String[] parts = keywords.toLowerCase().split("\\P{L}+");
//
//		List<String> filtered = Arrays.stream(parts)
//				.filter(interest -> interest.length() >= 2)
//				.filter(keyword -> !keyword.trim().isEmpty())
//				.distinct()
//				.peek(keyword -> System.out.println("After distinct: " + keyword))
//				.collect(Collectors.toList());
//
//		return String.join(",", filtered);
//	}

}

