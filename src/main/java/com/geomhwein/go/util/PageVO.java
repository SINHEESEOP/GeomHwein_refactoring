package com.geomhwein.go.util;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lombok.Data;

@Data
public class PageVO {

	private int startPage; //게시판 화면에 보여질 첫페이지 번호
	private int endPage; //게시판 화면에 보여질 끝페이지 번호
	private boolean prev; //다음 이전 활성화 여부
	private boolean next; //이전버튼 활성화 여부
	
	private int pageNum; //현재 조회하는 페이지 번호
	private int amount; //한 페이지에서 몇개의 데이터를 보여줄 건가
	private int total; //총 게시물 수
	
	private int realEnd; //진짜끝번호
	private List<Integer> pageList; //페이지 번호리스트(*타임리프에서는 향상된for문을 쓰기위해 list로 페이지번호생성)
	
	private Criteria cri; //페이징 기준
	
	public PageVO(Criteria cri, int total) {
			this.cri = cri; 
			this.total = total; 
			this.pageNum = cri.getPageNum(); 
			this.amount = cri.getAmount(); 
			
			this.endPage = (int)(Math.ceil(this.pageNum / 10.0)  ) * 10;
			
			this.startPage = endPage - 10 + 1; 
			
			this.realEnd = (int)Math.ceil(total / (double)this.amount );
			
			if(this.endPage > realEnd ) this.endPage = realEnd;
			
			this.prev = this.startPage > 1;
			
			this.next = realEnd > this.endPage;
			this.pageList = IntStream.rangeClosed(this.startPage, this.endPage).boxed().collect(Collectors.toList());
	}
}
