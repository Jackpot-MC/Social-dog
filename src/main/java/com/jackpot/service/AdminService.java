package com.jackpot.service;

import java.io.IOException;

import com.jackpot.domain.AdminVO;

public interface AdminService {
	
	public AdminVO get(String adminLoginId);
	
	public void register(AdminVO admin);
	
	public void modify(AdminVO admin) throws IOException;

	public boolean remove(Long adminId);
	
	public boolean removeLoginId(String adminLoginId);
}
