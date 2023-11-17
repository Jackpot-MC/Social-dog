package com.jackpot.security;

import com.jackpot.domain.MemberVO;
import com.jackpot.mapper.MemberMapper;
import lombok.extern.log4j.Log4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Log4j
public class MemberDetailsServiceImpl implements UserDetailsService {
    MemberMapper memberMapper;
    @Override
    public UserDetails loadUserByUsername(String memberLoginId) throws UsernameNotFoundException {
        log.warn("Load Member by Member Login ID: " + memberLoginId);

        MemberVO vo = memberMapper.get(memberLoginId);
        if(vo == null){
            throw new UsernameNotFoundException(memberLoginId + "은/는 없는 ID입니다.");
        }

        log.warn("member vo : " + vo);
        return vo;
    }
}
