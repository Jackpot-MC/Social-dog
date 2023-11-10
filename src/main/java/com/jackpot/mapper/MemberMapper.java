package com.jackpot.mapper;


import com.jackpot.domain.AuthVO;
import com.jackpot.domain.MemberVO;

public interface MemberMapper {
	public MemberVO read(String username);
	
	public void insert(MemberVO member);
	
	// insert부터 해서 username이라는 foreign키를 등록한 후에 실행해야 오류가 나지 않음
	public void insertAuth(AuthVO auth);
	
	public void modify(MemberVO member);
	
	// insert부터 해서 username이라는 foreign키를 등록한 후에 실행해야 오류가 나지 않음
	public void modifyAuth(AuthVO auth);
	
}
