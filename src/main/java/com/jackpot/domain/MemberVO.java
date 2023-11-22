package com.jackpot.domain;

import java.util.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberVO {


	private Long memberId;
	
	@NotBlank(message = "이름을 입력해주세요.")
	private String memberName;
	
	@NotNull(message = "생년월일을 입력해주세요.")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date memberBirth;
	
	@NotBlank(message = "아이디를 입력해주세요.")
	private String loginId;
	
	@NotBlank(message = "비밀번호를 입력해주세요.")
	private String loginPwd;

	@NotBlank(message = "비밀번호를 다시 입력해주세요.")
	private String loginPwd2;
	
	@NotBlank(message = "이메일을 입력해주세요.")
	@Email(message = "email 형식에 맞지 않습니다.")
	private String memberEmail;
	
	@NotBlank(message = "주소를 입력해주세요.")
	private String memberAddress;

	private List<AuthVO> authList;

	public Collection<SimpleGrantedAuthority> getAuthorities(){
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		for(AuthVO auth: authList){
			authorities.add(new SimpleGrantedAuthority(auth.getAuth()));
		}
		return authorities;
	}
}
