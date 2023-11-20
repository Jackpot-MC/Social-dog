package com.jackpot.domain;

import org.springframework.web.util.UriComponentsBuilder;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class ReviewCriteria {

	private int pageNum;
	private int amount;
	
	
	public ReviewCriteria() {
		this(1, 10);
	}
	
	public ReviewCriteria(int pageNum) {
		this(pageNum, 10);
	}
	
	public ReviewCriteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}
	
	public int getOffset() {
		return (pageNum -1) * amount;
	}
	
	
	public String getLink(String base, int pageNum) {
		UriComponentsBuilder builder =
			UriComponentsBuilder.fromPath(base)
				.queryParam("pageNum", pageNum)
				.queryParam("amount", amount);
		return builder.toUriString();
	}
	
	public String getLink() {
		return getLink("", pageNum);
	}
	
	public String getLink(int pageNum) {
		return getLink("", pageNum);
	}
	
	public String getLink(String base) {
		return getLink(base, pageNum);
	}
	
	public String getLinkWithReviewId(String base, Long reveiwId) { //Criteria에 없는 정보를 더 추가
		return getLink(base, pageNum) + "&reveiwId=" + reveiwId;
	}
	
}
