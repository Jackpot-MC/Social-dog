package com.jackpot.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

// @Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminVO implements UserDetails {

	private Long adminId;
	
	@NotBlank(message = "관리자 id는 필수 항목입니다.")
	private String adminLoginId;
	
	@NotBlank(message = "관리자 pwd는 필수 항목입니다.")
	private String adminLoginPwd;

	@NotBlank(message = "관리자 pwd 확인은 필수 항목입니다.")
	private String adminLoginPwd2;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}

	@Override
	public String getPassword() {
		return this.adminLoginPwd;
	}

	@Override
	public String getUsername() {
		return this.adminLoginPwd;
	}

	@Override
	public boolean isAccountNonExpired() {
		//계정 만료되지 않았나요? -> 만료 구현 예정없음 무조건 true
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		//계정 안잠겨있나요? => 계정 비활성화 구현 예정없음 -> 무조건 true
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		//비밀번호가 만료되지 않았나요? -> 이하동문 무조건 true
		return true;
	}

	@Override
	public boolean isEnabled() {
		//사용가능한 계정인가요? => 사용안될 이유가 없음
		return true;
	}
}
