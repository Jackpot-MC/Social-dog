package com.jackpot.domain;

import lombok.Data;
import lombok.extern.log4j.Log4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
@Log4j
public class MemberVO {
	@NotBlank(message = "사용자 id는 필수 항목입니다.")
	@Size(min = 4, message = "사용자 id는 4글자 이상이어야 합니다.")
	private String username;
	
	@NotBlank(message = "비밀번호는 필수 항목입니다.")
	private String password;
	
	@NotBlank(message = "비밀번호 확인은 필수 항목입니다.")
	private String password2;
	
	@NotBlank(message = "email은 필수 항목입니다.")
	@Email(message = "email 형식에 맞지 않습니다.")
	private String email;
	
	private Date regDate;
	private Date updateDate;
	
	private List<AuthVO> authList;
	
	public Collection<SimpleGrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
		
		for(AuthVO auth: authList) {
			log.warn("================================"  + auth);
			authorities.add(new SimpleGrantedAuthority(auth.getAuth()));
		}
		
		return authorities;
	}
}
