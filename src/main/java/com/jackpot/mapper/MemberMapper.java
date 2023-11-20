package com.jackpot.mapper;


import java.util.List;

import com.jackpot.domain.MemberVO;

public interface MemberMapper {

	// 회원정보조회
	public MemberVO get(String memberLoginId);

	// 회원가입
	public void signup(MemberVO member);
	
	// 회원정보수정
	public int update(MemberVO member);
	
	// 회원정보삭제 (회원탈퇴)
	public int delete(String memberLoginId);

	public List<MemberVO> getList();
	
	// 로그인
	public MemberVO login(MemberVO member);
	
}
