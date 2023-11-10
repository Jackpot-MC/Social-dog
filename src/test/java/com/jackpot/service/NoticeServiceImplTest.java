package com.jackpot.service;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.jackpot.config.RootConfig;
import com.jackpot.domain.NoticeVO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes= {RootConfig.class})
@Log4j
public class NoticeServiceImplTest {

	@Autowired
	private NoticeService service;
	
//	@Test
//	public void testGet() {
//		log.info(service.get(1L));
//	}

//	@Test
//	public void testRegister() throws IOException {
//		NoticeVO notice = new NoticeVO();
//		notice.setNoticeTitle("작성한 제목 추가");
//		notice.setNoticeContent("작성한 내용 추가추가입니다.");
//		notice.setAdminId(1L);
//		log.info(notice);
//		
//		service.register(notice);
//		
//		log.info(notice);
//	}

	@Test
	public void testModify() throws IOException {
		NoticeVO notice = service.get(1L);
		
		log.info(notice);
		
		if(notice == null) {
			return;
		}
		
		notice.setNoticeTitle("제목입니다 수정");
		log.warn(notice);
		
		int count = service.modify(notice);
		
		log.warn(count);		
	}

//	@Test
//	public void testRemove() {
//		log.info("DELETE COUNT: " + service.remove(5L));
//	}

}
