package com.jackpot.domain;

import java.util.Date;

import lombok.Data;

@Data
public class ReplyVO {
		private Long no;
		private Long cno;	//Comment의 no
		
		private String content;
		private String loginId;
		private Date regDate;
		private Date updateDate;
}
