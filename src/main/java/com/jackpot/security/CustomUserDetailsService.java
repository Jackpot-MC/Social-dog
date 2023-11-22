package com.jackpot.security;

import com.jackpot.domain.MemberVO;
import com.jackpot.mapper.MemberMapper;
import com.jackpot.security.domain.CustomUser;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Log4j
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    MemberMapper memberMapper;

    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        log.warn("Load vo By  Login Id: " + loginId);


        MemberVO vo = memberMapper.get(loginId);
        log.warn("user vo: " + vo);
        if(vo == null) {
            log.warn(loginId + "은 없는 id입니다.");
            throw new UsernameNotFoundException(loginId + "은 없는 id입니다.");
        }

        log.warn("user vo: " + vo);
        return new CustomUser(vo);

    }
}
