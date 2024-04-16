package com.geomhwein.go.util;

import lombok.Data;

@Data
public class Criteria {

	private int pageNum;
	private int amount;
	
	public Criteria() {
		
		this.pageNum = 1;
		this.amount = 10;
	}

	public Criteria(int pageNum, int amountCount) {
		this.pageNum = pageNum;
		this.amount = amountCount;
	}
	
	public int getPageStart() {
		return (pageNum - 1) * amount;
	}
	
	private String searchFilter = "new";
	private String searchInput;
}
