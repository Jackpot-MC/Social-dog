//package com.jackpot.security;
//
//import com.jackpot.domain.MemberVO;
//import com.jackpot.mapper.MemberMapper;
//import com.jackpot.security.domain.CustomUser;
//import lombok.extern.log4j.Log4j;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//@Log4j
//public class CustomUserDetailsService implements UserDetailsService {
//	
//	@Autowired
//	private MemberMapper mapper;
//
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		
//		log.warn("Load User By Username: " + username);
//		
//		MemberVO vo = mapper.read(username);
//		if(vo == null) {
//			throw new UsernameNotFoundException(username + "은 없는 ID입니다.");
//		}
//		
//		log.warn("user vo: " + vo);		
//		return new CustomUser(vo);
//		
//		// ID 없으면 UsernameNotFoundException 예외 발생, 에러 메시지 출력
//		// Form에 입력된 Password와 부모 User에 들어간 Password 비교. Password 일치하면 redirect
//		// 성공하면 CustomUser를 Session Scope의 Security Context 안에 "principle"에 저장 --> principle로 사용할 수 있게 됨
//	}
//
//}
