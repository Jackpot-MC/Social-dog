package com.jackpot.service;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.jackpot.config.RootConfig;
import com.jackpot.domain.AdminVO;
import com.jackpot.mapper.AdminMapperTest;

import lombok.extern.log4j.Log4j;
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes= {RootConfig.class})
@Log4j
public class AdminServiceImplTest {

	@Autowired
	private AdminService service;
	
//	@Test
//	public void testGet() {
//		fail("Not yet implemented");
//	}

	@Test
	public void testRegister(){
		AdminVO admin = new AdminVO();
		admin.setAdminId(3);
		admin.setAdminLoginId("admin3");
		admin.setAdminLoginPwd("admin3");
		
		service.register(admin);
		
		log.info("생성된 게시물의 번호: " + admin.getAdminId());
	}
//
//	@Test
//	public void testModify() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testRemove() {
//		fail("Not yet implemented");
//	}

}
