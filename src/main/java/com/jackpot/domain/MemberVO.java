package com.jackpot.domain;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberVO {


	private Long memberId;
	
	@NotBlank(message = "사용자 이름은 필수 항목입니다.")
	private String memberName;
	
	@NotBlank(message = "사용자 나이는 필수 항목입니다.")
	private int memberAge;
	
	@NotBlank(message = "사용자 아이디는 필수 항목입니다.")
	@Size(min = 4, message = "사용자 id는 4글자 이상이어야 합니다.")
	private String memberLoginId;
	
	@NotBlank(message = "사용자 비밀번호는 필수 항목입니다.")
	@Size(min = 4, message = "사용자 비밀번호는 4글자 이상이어야 합니다.")
	private String memberLoginPwd;
	
	@NotBlank(message = "사용자 이메일은 필수 항목입니다.")
	@Email(message = "email 형식에 맞지 않습니다.")
	private String memberEmail;
	
	@NotBlank(message = "사용자 주소는 필수 항목입니다.")
	private String memberAddress;
	
}
