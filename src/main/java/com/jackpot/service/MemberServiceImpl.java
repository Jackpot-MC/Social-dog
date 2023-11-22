package com.jackpot.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
	public MemberVO get(String memberLoginId) {
		return memberMapper.get(memberLoginId);
	}
	
	@Override
	public void signup(MemberVO member) throws IOException {
		log.info("signup..." + member);
		//1.비번 암호화
		String encPassword = pwEncoder.encode(member.getPassword());
		member.setLoginPwd(encPassword);
		member.setLoginPwd2(encPassword);

		//2.member 저장
		memberMapper.signup(member);
	}

	@Override
	public void update(MemberVO member) throws IOException {
		log.info("update..." + member);

		String encPassword = pwEncoder.encode(member.getPassword());
		member.setLoginPwd(encPassword);
		member.setLoginPwd2(encPassword);

		memberMapper.update(member);
	}

	@Override
	public int delete(String memberLoginId) throws IOException {
		return memberMapper.delete(memberLoginId);
	}
	
	@Override
	public List<MemberVO> getList() {
		return memberMapper.getList();
	}
	
//	@Override
//	public MemberVO login(MemberVO member) {
//		return memberMapper.login(member);
//	}


}
