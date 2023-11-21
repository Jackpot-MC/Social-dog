package com.jackpot.security;

import com.jackpot.domain.AdminVO;
import com.jackpot.domain.MemberVO;
import com.jackpot.mapper.AdminMapper;
import com.jackpot.mapper.MemberMapper;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Log4j
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    MemberMapper memberMapper;

    @Autowired
    AdminMapper adminMapper;

    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        log.warn("Load vo By  Login Id: " + loginId);


        if(adminMapper.get(loginId) != null){
            AdminVO vo = adminMapper.get(loginId);
            log.warn("admin vo : " + vo);
            return vo;
        }
        else if(memberMapper.get(loginId)!=null){
            MemberVO vo = memberMapper.get(loginId);
            log.warn("member vo : " + vo);
            return vo;
        }
        else {
            throw new UsernameNotFoundException(loginId + "은/는 없는 ID입니다.");
        }

        // ID 없으면 UsernameNotFoundException 예외 발생, 에러 메시지 출력
        // Form에 입력된 Password와 부모 User에 들어간 Password 비교. Password 일치하면 redirect
        // 성공하면 CustomUser를 Session Scope의 Security Context 안에 "principle"에 저장 --> principle로 사용할 수 있게 됨
    }
}
