package com.jackpot.domain;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class ReviewVO {
	private long reviewId;

	@NotBlank(message = "제목은 필수 항목입니다.")
	private String reviewTitle;
	
	@NotBlank(message = "내용은 필수 항목입니다.")
	private String reviewContent;
	
	private Date reviewDate;
	
	@NotBlank(message = "별점은 필수 항목입니다.")
	private Double rating;
	
}
