package com.jackpot.mapper;


import java.util.List;

import com.jackpot.domain.AuthVO;
import com.jackpot.domain.MemberVO;

public interface MemberMapper {

	// 회원정보조회
	public MemberVO get(String loginId);

	// 회원가입
	public void signup(MemberVO member);
	
	// 회원정보수정
	public int update(MemberVO member);
	
	// 비밀번호수정
	public int pwdupdate(MemberVO member);
	
	// 회원정보삭제 (회원탈퇴)
	public int delete(String loginId);

	public List<MemberVO> getList();
	
	public void insertAuth(AuthVO auth);
	
	public Long getMemberIdByLoginId(String loginId);
}
