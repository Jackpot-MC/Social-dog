package com.jackpot.mapper;

import com.jackpot.domain.AdminVO;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface AdminMapper {
	
	public AdminVO get(String adminLoginId); // 로그인 아이디 전달
	
	public void insert(AdminVO admin);
	
	public int update(AdminVO admin);
	
	public int delete(Long adminId); // PK 전달
	
	public int deleteLoginId(String adminLoginId); // 로그인 아이디 전달

	public AdminVO login(AdminVO adminVO);//로그인
}
