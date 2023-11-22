package com.jackpot.domain;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;

import com.jackpot.service.NoticeService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoticeVO {

	private Long noticeId;
	
	@NotBlank(message = "공지사항 제목은 필수 항목입니다.")
	private String noticeTitle;
	
	@NotBlank(message = "공지사항 내용은 필수 항목입니다.")
	private String noticeContent;
	
	private Long memberId; //FK
	private String memberLoginId; //FK
	private String memberLoginPwd;

	private Date regDate;
	private Date updateDate;
	
}
