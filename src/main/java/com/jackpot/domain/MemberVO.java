package com.jackpot.domain;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

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
public class MemberVO implements UserDetails{


	private Long memberId;
	
	@NotBlank(message = "이름을 입력해주세요.")
	private String memberName;
	
	@NotNull(message = "생년월일을 입력해주세요.")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date memberBirth;
	
	@NotBlank(message = "아이디를 입력해주세요.")
	private String memberLoginId;
	
	@NotBlank(message = "비밀번호를 입력해주세요.")
	private String memberLoginPwd;

	@NotBlank(message = "비밀번호를 다시 입력해주세요.")
	private String memberLoginPwd2;
	
	@NotBlank(message = "이메일을 입력해주세요.")
	@Email(message = "email 형식에 맞지 않습니다.")
	private String memberEmail;
	
	@NotBlank(message = "주소를 입력해주세요.")
	private String memberAddress;


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
	}

	@Override
	public String getPassword() {
		return this.memberLoginPwd;
	}

	@Override
	public String getUsername() {
		return this.memberLoginId;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
