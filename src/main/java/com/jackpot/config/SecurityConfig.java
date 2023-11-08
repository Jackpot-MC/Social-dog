package com.jackpot.config;

import javax.sql.DataSource;

import org.galapagos.security.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

import lombok.extern.log4j.Log4j;

@Configuration		// 설정이라는 뜻
@EnableWebSecurity
@Log4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DataSource dataSource;
	
	@Bean
	public UserDetailsService customUserService() {
		return new CustomUserDetailsService();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
		repo.setDataSource(dataSource);
		
		return repo;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		CharacterEncodingFilter filter = new CharacterEncodingFilter();
		filter.setEncoding("UTF-8");
		filter.setForceEncoding(true);
		
		http.addFilterBefore(filter, CsrfFilter.class); // csrf 토큰 처리하는 필터보다 앞에다 이 문자셋 필터를 놓아라
		
		http.csrf().ignoringAntMatchers("/api/**");
		
		http.authorizeRequests()
			.antMatchers("/security/profile").authenticated() // 로그인 한 사람만 접근 가능함
			.antMatchers(
					"/board/register",
					"/board/modify",
					"/board/remove").authenticated() // 로그인 한 사람만 접근 가능함
			.antMatchers(
					"/travel/register", 
					"/travel/modify",
					"/travel/remove").access("hasRole('ROLE_MANAGER')"); // MANAGER 이상인 ADMIN도 적용됨
		
		http.formLogin()	// 로그인 설정 시작
			.loginPage("/security/login?error=login_required")	// 로그인 페이지 URL. GET요청. 로그인 안 하고 접근한 경우 리다이렉트
			.loginProcessingUrl("/security/login")	//로그인 POST URL. Security가 해 줌. Form action에 제시할 URL
			.defaultSuccessUrl("/")		// 로그인 성공시 이동할 페이지. 디폴트 home 페이지
			.failureUrl("/security/login?error=true");	//el에서 접근 : param.error
		
		http.logout()
			.logoutUrl("/security/logout")		// 로그아웃 호출 url. post일 경우
			.invalidateHttpSession(true)		// 세션invalidate
			.deleteCookies("remember-me", "JSESSION-ID")	// 삭제할 쿠키 목록
			.logoutSuccessUrl("/");	//로그아웃 이후 이동할 페이지. get
		
		http.rememberMe()	//remember-me 기능 설정
			.key("Galapagos")	// 암호화 할 때 사용한 보안 문자열. 이게 노출되면 암호화를 풀 수 있으므로, 보통은 난수 형태의 긴 문자열을 사용함.
			.tokenRepository(persistentTokenRepository())
			.tokenValiditySeconds(7*24*60*60);	// 7일 후 만료. 만약 명시적으로 logout을 해 줄 경우, logout에서 remember-me 쿠키를 삭제하므로 사라짐.
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		log.info("configure......................");
		
/* 위의 http로 비번 처리를 해야함. 이 부분은 메모리에 직접 접근하는 방법. 테스트용.
		 * auth.inMemoryAuthentication() // 메모리에서 사용자 정보 설정 .withUser("admin") //
		 * username, 사용자id
		 * .password("$2a$10$YpK24Ik1JCkZUCSMM5rEI.1lRLVdiamjr.Fp0SIqD7b3KFVr7yqx6")
		 * .roles("ADMIN"); // 역할 설정. 앞에 ROLE_ 안 붙여도 자동으로 붙음
		 * 
		 * auth.inMemoryAuthentication() .withUser("member") // .password("{noop}1234")
		 * // 비밀번호, {noop}는 암호화 없음 의미 (no operation 약자)
		 * .password("$2a$10$YpK24Ik1JCkZUCSMM5rEI.1lRLVdiamjr.Fp0SIqD7b3KFVr7yqx6")
		 * .roles("MEMBER");
		 */
		
		auth.userDetailsService(customUserService())
			.passwordEncoder(passwordEncoder());
		
	}

}
