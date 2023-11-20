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
	
	@NotBlank(message = "사용자 이름은 필수 항목입니다.")
	private String memberName;
	
	@NotNull(message = "사용자 나이는 필수 항목입니다.")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date memberBirth;
	
	@NotBlank(message = "사용자 아이디는 필수 항목입니다.")
	private String memberLoginId;
	
	@NotBlank(message = "사용자 비밀번호는 필수 항목입니다.")
	private String memberLoginPwd;

	@NotBlank(message = "사용자 비밀번호 확인은 필수 항목입니다.")
	private String memberLoginPwd2;
	
	@NotBlank(message = "사용자 이메일은 필수 항목입니다.")
	@Email(message = "email 형식에 맞지 않습니다.")
	private String memberEmail;
	
	@NotBlank(message = "사용자 주소는 필수 항목입니다.")
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
