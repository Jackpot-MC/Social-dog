package com.jackpot.domain;

import org.springframework.web.util.UriComponentsBuilder;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class Criteria {

	private int pageNum;
	private int amount;
	private String type;
	private String keyword;

	public Criteria() {
		this(1, 6);
	}

	public Criteria(int pageNum) {
		this(pageNum, 6);
	}

	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}

	public int getOffset() {
		return (pageNum - 1) * amount;
	}

	public String[] getTypeArr() {
		return type == null ? new String[] {} : // 빈 배열 리턴
				type.split(""); // 한글자 단위로 분리된 배열 리턴
	}

	//jsp에서 번거로운 부분을 java부분에서 처리 가능
	public String getLink(String base, int pageNum) {
		UriComponentsBuilder builder = 
				UriComponentsBuilder.fromPath(base)
					.queryParam("pageNum", pageNum)
					.queryParam("amount", amount)
					.queryParam("type", type)
					.queryParam("keyword", keyword);
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

	public String getLinkWithBno(String base, Long bno) {
		return getLink(base, pageNum) + "&bno=" + bno;
	} // criteria에서 만들어주는 문자열 + bno 정보 추가
}
