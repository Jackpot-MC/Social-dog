package com.jackpot.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.jackpot.config.RootConfig;
import com.jackpot.domain.ReviewVO;

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
//		Date date = date1.parse("2077-07-07");
//
//		System.out.println("dog DB에 데이터 삽입 시작");
//
//		
//		vo.setMemberId(2L);
//		vo.setPlaceId(1L);
//		vo.setReviewTitle("리뷰타이틀");
//		vo.setReviewContent("콘텐츠콘텐츠콘텐츠");
//		vo.setReviewDate(date);
//		vo.setRating(4.3);
//			
//		mapper.create(vo);
//		log.info(vo);
//
//		System.out.println("데이터 삽입 끝!");
//	}
//
	@Test
	public void testGet() {
		ReviewVO vo = mapper.get(3L);

		System.out.println("get 시작");
		
		log.info(vo);
		log.warn(vo);
		
		System.out.println("get 끝!");
	}

//	@Test
//	public void testUpdate() {
//		DogVO dog = mapper.get(4L);
//
//		System.out.println("11111111111111111111111111111111");
//		log.info(dog);
//		
//		SimpleDateFormat date1 = new SimpleDateFormat("yyyy-MM-dd");
//		Date date = date1.parse("2000-04-01");
//		
//		System.out.println("update 시작");
//		
//		dog.setDogName("수정멍1");
//		dog.setDogKind("수정개");
//		dog.setDogBirth(date);
//		dog.setDogDescription("꼬리 치며 반갑다고 멍멍멍");
//		dog.setDogFavorite("Update 테스트 전송");
//		dog.setDogPhotoPath("c:/temp/temp3.jpg");
//		
//		log.info(dog);
//				
//		mapper.update(dog);
//		
//		System.out.println("update 끝!!!");
//	}

//	@Test
//	public void testDelete() {
//      log.info("DELETE: " + mapper.delete(2L));
//	}

//	@Test
//	public void testGetList() {
//	      log.info(mapper.getList());
//	}

}
