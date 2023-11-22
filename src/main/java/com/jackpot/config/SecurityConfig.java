package com.jackpot.config;

import com.jackpot.security.MemberDetailsServiceImpl;

import lombok.extern.log4j.Log4j;

<<<<<<< HEAD
=======
import com.jackpot.security.CustomUserDetailsService;
>>>>>>> 56f93344b523950555d61292c95e97e6fd370093
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
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

import javax.sql.DataSource;

@Configuration
@Order(2)
@EnableWebSecurity
@Log4j
public class MemberSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    protected void configure(HttpSecurity http) throws Exception {

        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);

        http.addFilterBefore(filter, CsrfFilter.class);

        http.csrf().ignoringAntMatchers("/api/**");

        http.authorizeRequests()
                .antMatchers(
<<<<<<< HEAD
                        "/",
                        "/review/*",
                        "/participant/*",
                        "/appointment/*",
                        "/profile/*"
=======
                        "/notice/modify",
                        "/notice/register",
                        "/notice/remove")
                .access("hasRole('ROLE_ADMIN')")
                .antMatchers(
                        "/review/*",
                        "/participant/*",
                        "/appointment/*",
                        "/profile/*",
                        "/security/update"
>>>>>>> 56f93344b523950555d61292c95e97e6fd370093
                ).access("hasRole('ROLE_USER')");//USER이상은 다적용


        http.formLogin()
<<<<<<< HEAD
                .loginPage("/member/login?error=login_required")    // 로그인 안하고 접근한 경우 리다이렉트
                .loginProcessingUrl("/member/login")
                .defaultSuccessUrl("/")
                .failureUrl("/member/login?error=true");
//                .usernameParameter("memberLoginId")
//                .passwordParameter("memberLoginPwd");    // el : param.error
=======
                .loginPage("/security/login?error=login_required")    // 로그인 안하고 접근한 경우 리다이렉트
                .loginProcessingUrl("/security/login*")
                .defaultSuccessUrl("/")
                .failureUrl("/security/login?error=true");
        // el : param.error
>>>>>>> 56f93344b523950555d61292c95e97e6fd370093


        http.logout()                        // 로그아웃 설정 시작
                .logoutUrl("/member/logout")    // POST: 로그아웃 호출 url
                .invalidateHttpSession(true)    // 세션 invalidate
<<<<<<< HEAD
                .deleteCookies("JSESSION-ID")    // 삭제할 쿠키 목록
                .logoutSuccessUrl("/member/login");    // 로그아웃 이후 이동할 페이지
=======
                .deleteCookies("JSESSION-ID", "remember-me")    // 삭제할 쿠키 목록
                .logoutSuccessUrl("/security/login");    // 로그아웃 이후 이동할 페이지
>>>>>>> 56f93344b523950555d61292c95e97e6fd370093


//        http.rememberMe()        // remember-me 기능 설정
//                .key("Galapagos")
//                .tokenRepository(persistentTokenRepository())
//                .tokenValiditySeconds(7 * 24 * 60 * 60);    // 7일

    }


    @Bean
    public UserDetailsService customUserService() {
<<<<<<< HEAD
        return new MemberDetailsServiceImpl();
=======
        return new CustomUserDetailsService();
>>>>>>> 56f93344b523950555d61292c95e97e6fd370093
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)//사용자 인증방식
            throws Exception {

        auth
                .userDetailsService(customUserService())
                .passwordEncoder(passwordEncoder());

    }


    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
        repo.setDataSource(dataSource);

        return repo;
    }
}
