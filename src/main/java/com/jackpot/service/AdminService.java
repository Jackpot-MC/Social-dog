package com.jackpot.service;

import java.io.IOException;

import com.jackpot.domain.AdminVO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AdminService {
	
	public AdminVO get(Long adminId);
	
	public void register(AdminVO admin);
	
	public void modify(AdminVO admin) throws IOException;

	public boolean remove(Long adminId);
	
	public boolean removeLoginId(String adminLoginId);
}
