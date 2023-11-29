package com.jackpot.mapper;

import java.io.IOException;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.jackpot.config.RootConfig;
import com.jackpot.domain.MemberVO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RootConfig.class })
@Log4j
@WebAppConfiguration
public class MemberMapperTest {

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

		member.setLoginId("user3");
		member.setLoginPwd("user1234");
		member.setMemberName("김길동");
		member.setMemberBirth(new Date("2011-02-11"));
		member.setMemberEmail("user3@gmail.com");
		member.setMemberAddress("서울시 강남구");

		memberMapper.signup(member);
	}	
	
	//@Test
	public void testUpdate() {
		MemberVO member = memberMapper.get("user1");
		
		member.setLoginPwd("update1234");
		memberMapper.update(member);
	}
	
	@Test
	public void testDelete() {
		memberMapper.delete("user2");

	}
}
