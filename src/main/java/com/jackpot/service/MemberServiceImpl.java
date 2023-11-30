package com.jackpot.service;

import java.io.IOException;
import java.util.List;
import com.jackpot.domain.AuthVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.jackpot.domain.MemberVO;
import com.jackpot.mapper.MemberMapper;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	MemberMapper memberMapper;

	@Autowired
	private PasswordEncoder pwEncoder;

	@Override
	public MemberVO get(String loginId) {
		return memberMapper.get(loginId);
	}
	
	@Override
	public void signup(MemberVO member) throws IOException {
		log.info("signup..." + member);
		//1.비번 암호화
		String encPassword = pwEncoder.encode(member.getLoginPwd());
		member.setLoginPwd(encPassword);
		member.setLoginPwd2(encPassword);

		//2.member 저장
		memberMapper.signup(member);

		//3.auth테이블에 저장
		AuthVO auth = new AuthVO(member.getLoginId(),
				"ROLE_USER", member.getMemberId());

		memberMapper.insertAuth(auth);
	}

	@Override
	public void update(MemberVO member) throws IOException {
		log.info("update..." + member);

		memberMapper.update(member);
	}
	
	@Override
	public void pwdupdate(MemberVO member) throws IOException {
		log.info("password update..." + member);

		String encPassword = pwEncoder.encode(member.getLoginPwd());
		member.setLoginPwd(encPassword);
		member.setLoginPwd2(encPassword);
		
		memberMapper.pwdupdate(member);
	}

	@Override
	public int delete(String loginId) throws IOException {
		return memberMapper.delete(loginId);
	}
	
	@Override
	public List<MemberVO> getList() {
		return memberMapper.getList();
	}

}
