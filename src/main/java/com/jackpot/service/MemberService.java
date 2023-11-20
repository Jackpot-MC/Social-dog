package com.jackpot.service;

import java.io.IOException;
import java.util.List;

import com.jackpot.domain.MemberVO;

public interface MemberService {

	public MemberVO get(String memberLoginId);
	
	public void signup(MemberVO member) throws IOException;
	
	public void update(MemberVO member) throws IOException;
	
	public int delete(String memberId)throws IOException;

	public List<MemberVO> getList() throws IOException;
	
	public MemberVO login(MemberVO member) throws IOException;


}
