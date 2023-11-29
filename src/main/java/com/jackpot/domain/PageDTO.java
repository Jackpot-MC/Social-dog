package com.jackpot.domain;

import lombok.Data;

@Data
public class PageDTO {

	private int startPage; // 페이지 블럭의 첫 페이지 번호
	private int endPage; // 페이지 블럭의 끝 페이지 번호
	private boolean prev; // 이전 페이지 존재 여부
	private boolean next; // 다음 페이지 존재 여부
	
	private int totalPage; // 전체 페이지 수
	private int total; // 전체 데이터 건수
	
	private Criteria cri; // 페이지 요청 정보 객체

	public PageDTO(Criteria cri, int total) {
		this.cri = cri;
		this.total = total; // 전체 데이터 건수
		
		int amount = cri.getAmount();
		int page = cri.getPageNum();
		
		totalPage = (int) (Math.ceil((float) total / amount));
		endPage = (int) (Math.ceil(page / (double) amount)) * amount;
		startPage = endPage - (amount - 1);
		
		int realEnd = (int) (Math.ceil((total * 1.0) / amount));
		if (realEnd < this.endPage) {
			endPage = realEnd;
		}
		
		prev = startPage > 1;
		next = endPage < realEnd;
	}
}
