package com.jackpot.domain;

import org.springframework.web.util.UriComponentsBuilder;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class AppointmentCriteria {

	private int pageNum;
	private int amount;
	private String type;	// 검색 대상
	private String keyword;	// 검색어
	
	
	public AppointmentCriteria() {
		this(1, 10);
	}
	
	public AppointmentCriteria(int pageNum) {
		this(pageNum, 10);
	}
	
	public AppointmentCriteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}
	
	public int getOffset() {
		return (pageNum -1) * amount;
	}
	
	public String[] getTypeArr() {
		return type == null ?
						new String[] {} :	// 빈 배열 리턴
						type.split("");		// 한 글자 단위로 분리된 배열 리턴. typeArr이라는 배열을 리턴
	}
	
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
	
	public String getLinkWithAppointmentId(String base, Long appointmentId) { //Criteria에 없는 정보를 더 추가
		return getLink(base, pageNum) + "&memberId=" + appointmentId;
	}
	
}
