package com.jackpot.mapper;

import static org.junit.Assert.fail;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

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
public class NoticeMapperTest {

	@Autowired
	private NoticeMapper mapper;
	
	@Test
	public void testRead() {
		NoticeVO notice = mapper.read(1L);
		
		log.info(notice);
		log.warn("성공");
	}

//	@Test
//	public void testInsert() {
//		NoticeVO notice = new NoticeVO();
//		notice.setNoticeTitle("새로 작성하는 글");
//		notice.setNoticeContent("새로 작성하는 내용");
//		notice.setAdminId(7L);
//		
//		mapper.insert(notice);
//		
//		log.info(notice);
//	}

//	@Test
//	public void testUpdate() {
//		NoticeVO notice = new NoticeVO();
//		notice.setNoticeId(3L);
//		notice.setNoticeTitle("수정한 제목");
//		notice.setNoticeContent("수정한 내용");
//		log.info(notice);
//		
//		int count = mapper.update(notice);
//		
//		log.info(count);
//	}

//	@Test
//	public void testDelete() {
//		log.info("DELETE COUNT: " + mapper.delete(2L));
//	}

}
