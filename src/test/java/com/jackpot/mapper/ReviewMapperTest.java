package com.jackpot.mapper;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.jackpot.config.RootConfig;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RootConfig.class })
@WebAppConfiguration
@Log4j
public class ReviewMapperTest {

	@Autowired
	private ReviewMapper mapper;
	
//	@Test
//	public void testCreate() throws Exception {
//		ReviewVO vo = new ReviewVO();
//		
//		SimpleDateFormat date1 = new SimpleDateFormat("yyyy-MM-dd");
//		Date date = date1.parse("2077-07-08");
//
//		System.out.println("dog DB에 데이터 삽입 시작");
//
//		vo.setMemberId(5L);
//		vo.setPlaceId(6L);
//		vo.setReviewTitle("리뷰타이틀3");
//		vo.setReviewContent("콘텐츠콘텐츠콘텐츠3");
//		vo.setReviewDate(date);
//		vo.setRating(2.0);
//			
//		mapper.create(vo);
//		log.info(vo);
//
//		System.out.println("데이터 삽입 끝!");
//	}

//	@Test
//	public void testGet() {
//		ReviewVO vo = mapper.get(5L);
//
//		System.out.println("get 시작");
//		
//		log.info(vo);
//		log.warn(vo);
//		
//		System.out.println("get 끝!");
//	}

//	@Test
//	public void testUpdate() throws Exception {
//		ReviewVO vo = mapper.get(4L);
//
//		log.info(vo);
//		
//		SimpleDateFormat date1 = new SimpleDateFormat("yyyy-MM-dd");
//		Date date = date1.parse("1993-03-05");
//		
//		System.out.println("update 시작");
//		
//		vo.setReviewTitle("수정된 리뷰타이틀");
//		vo.setReviewContent("수정된 콘텐츠콘텐츠콘텐츠");
//		vo.setReviewDate(date);
//		vo.setRating(1.0);
//		
//		log.info(vo);
//				
//		mapper.update(vo);
//		
//		System.out.println("update 끝!!!");
//	}

//	@Test
//	public void testDelete() {
//      log.info("DELETE: " + mapper.delete(6L));
//	}

//	@Test
//	public void testGetList() {
//	      log.info(mapper.getList());
//	}

}
