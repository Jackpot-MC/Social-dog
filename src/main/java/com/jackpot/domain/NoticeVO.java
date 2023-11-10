package com.jackpot.domain;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoticeVO {
	
	private int noticeId;
	
	@NotBlank(message = "공지사항 제목은 필수 항목입니다.")
	private String noticeTitle;
	
	@NotBlank(message = "공지사항 내용은 필수 항목입니다.")
	private String noticeContent;
	
	private int adminId;
}
