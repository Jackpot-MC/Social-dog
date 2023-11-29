package com.jackpot.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.jackpot.config.RootConfig;
import com.jackpot.domain.MemberVO;
import com.jackpot.mapper.MemberMapper;
import com.jackpot.mapper.MemberMapperTest;

import lombok.extern.log4j.Log4j;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RootConfig.class })
@Log4j
@WebAppConfiguration
public class MemberServiceImplTest {

	@Autowired
	private MemberMapper memberMapper;
	
	//@Test
	public void testGet(){
		System.out.println("testGet....1");
		MemberVO member = memberMapper.get("user1");
		System.out.println("testGet....2");
		log.info(member);
	}
	
	//@Test
	public void testJoin() {
		MemberVO member = new MemberVO();

		member.setLoginId("user4");
		member.setLoginPwd("user1234");
		member.setMemberName("윤길동");
		member.setMemberBirth(new Date("2002-11-04"));
		member.setMemberEmail("user4@gmail.com");
		member.setMemberAddress("서울시 강남구");

		memberMapper.signup(member);
	}	
	
	//@Test
	public void testUpdate() {
		MemberVO member = memberMapper.get("user6");
		
		member.setLoginPwd("update1234");
		memberMapper.update(member);
	}
	
	@Test
	public void testDelete() {
		memberMapper.delete("user7");

	}
	
	

}
