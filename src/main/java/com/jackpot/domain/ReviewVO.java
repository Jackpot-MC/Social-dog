package com.jackpot.domain;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ReviewVO {
	private long reviewId;
	
	private long memberId;	//FK
	private long placeId;	//FK

	@NotBlank(message = "제목은 필수 항목입니다.")
	private String reviewTitle;
	
	@NotBlank(message = "내용은 필수 항목입니다.")
	private String reviewContent;
	
	private int rating;
	
	private Date regDate;
	private Date updateDate;
	
	private String memberLoginId; //login_id
	private String memberName; //member_name
}
