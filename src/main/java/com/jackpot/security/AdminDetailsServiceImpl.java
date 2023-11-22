package com.jackpot.security;

import com.jackpot.domain.AdminVO;
import com.jackpot.mapper.AdminMapper;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Log4j
public class AdminDetailsServiceImpl implements UserDetailsService {

    @Autowired
    AdminMapper adminMapper;
    @Override
    public UserDetails loadUserByUsername(String adminLoginId) throws UsernameNotFoundException {
        log.warn("Load Admin By Admin Login Id: " + adminLoginId);

        AdminVO vo = adminMapper.get(adminLoginId);
        if(vo == null) {
            throw new UsernameNotFoundException(adminLoginId + "은/는 없는 ID입니다.");
        }

        log.warn("admin vo : " + vo);
        return vo;

        // ID 없으면 UsernameNotFoundException 예외 발생, 에러 메시지 출력
        // Form에 입력된 Password와 부모 User에 들어간 Password 비교. Password 일치하면 redirect
        // 성공하면 CustomUser를 Session Scope의 Security Context 안에 "principle"에 저장 --> principle로 사용할 수 있게 됨
    }
}
