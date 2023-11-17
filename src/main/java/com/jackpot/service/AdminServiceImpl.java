package com.jackpot.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jackpot.domain.AdminVO;
import com.jackpot.mapper.AdminMapper;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class AdminServiceImpl implements AdminService {

	@Autowired
	AdminMapper mapper;
	
	@Override
	public AdminVO get(Long adminId) {
		return mapper.get(adminId);
	}

	@Override
	public void register(AdminVO admin){		
		log.info("insert......." + admin);
		
		mapper.insert(admin);
	}

	@Override
	public void modify(AdminVO admin) throws IOException {
		log.info("update......." + admin);
		
		mapper.update(admin);
	}

	@Override
	public boolean remove(Long adminId) {
		log.info("remove......." + adminId);
		
		return mapper.delete(adminId) == 1;
	}

	@Override
	public boolean removeLoginId(String adminLoginId) {
		log.info("removeLoginId......." + adminLoginId);
		
		return mapper.deleteLoginId(adminLoginId) == 1;
	}

}
