package com.jackpot.service;

import java.io.IOException;
import java.util.List;

import com.jackpot.domain.MemberVO;
import org.springframework.stereotype.Service;

@Service
public interface MemberService {

	public MemberVO get(String loginId);
	
	public void signup(MemberVO member) throws IOException;
	
	public void update(MemberVO member) throws IOException;
	
	public void pwdupdate(MemberVO member) throws IOException;
	
	public int delete(String memberId)throws IOException;

	public List<MemberVO> getList() throws IOException;
	
	public Long getMemberIdByLoginId(String loginId);
}
