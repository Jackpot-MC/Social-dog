package com.jackpot.security.domain;

import com.jackpot.domain.MemberVO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class CustomUser extends User {
    private MemberVO member;

    public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public CustomUser(MemberVO vo){
        super(vo.getLoginId(), vo.getLoginPwd(), vo.getAuthorities());
        this.member = vo;
    }
}
