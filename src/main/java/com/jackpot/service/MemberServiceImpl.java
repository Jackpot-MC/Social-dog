package com.jackpot.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jackpot.domain.MemberVO;
import com.jackpot.mapper.MemberMapper;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	MemberMapper memberMapper;

	@Override
	public MemberVO get(String memberLoginId) {
		return memberMapper.get(memberLoginId);
	}
	
	@Override
	public void signup(MemberVO member) throws IOException {
		member = new MemberVO();
		log.info("join..." + member);
		memberMapper.signup(member);
	}

	@Override
	public void update(MemberVO member) throws IOException {
		log.info("update..." + member);
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
	
	@Override
	public MemberVO login(MemberVO member) {
		return memberMapper.login(member);
	}


}
