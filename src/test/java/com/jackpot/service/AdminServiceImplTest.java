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
	
	@Test
	public void testGet() {
		log.info(service.get("admin4"));
	}

//	@Test
//	public void testRegister(){
//		AdminVO admin = new AdminVO();
//		admin.setAdminLoginId("admin2");
//		admin.setAdminLoginPwd("admin2");
//		log.info(admin);
//		
//		service.register(admin);
//		
//		log.info(admin);
//	}

//	@Test
//	public void testModify() throws IOException {
//		AdminVO admin = service.get("admin1");
//		
//		if(admin == null) {
//			return;
//		}
//		
//		admin.setAdminLoginPwd("admin112");
//		log.info(admin);
//		
//		service.modify(admin);
//		
//		log.info(admin);
//	}

//	@Test
//	public void testRemove() {
//		log.info("REMOVE RESULT: " + service.remove(5L));
//	}
	
//	@Test
//	public void testRemoveLoginId() {
//		log.info("REMOVE RESULT: " + service.removeLoginId("admin3"));
//	}

}
