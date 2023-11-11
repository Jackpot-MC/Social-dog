package com.jackpot.mapper;

import static org.junit.Assert.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.jackpot.config.RootConfig;
import com.jackpot.domain.DogVO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RootConfig.class })
@WebAppConfiguration
@Log4j
public class DogMapperTest {

	@Autowired
	private DogMapper mapper;

//	@Test
//	public void testCreate() throws Exception {
//		DogVO vo = new DogVO();
//		
//		SimpleDateFormat date1 = new SimpleDateFormat("yyyy-MM-dd");
//		Date date = date1.parse("3000-07-07");
//
//		System.out.println("dog DB에 데이터 삽입 시작");
//
//		vo.setDogName("수정과");
//		vo.setDogKind("식혜");
//		vo.setDogBirth(date);
//		vo.setDogDescription("데이터 수정 테스트용");
//		vo.setDogFavorite("수정하기");
//		vo.setDogPhotoPath("updateupdateupdate");
//
//		mapper.create(vo);
//		log.info(vo);
//
//		System.out.println("데이터 삽입 끝!");
//	}

//	@Test
//	public void testGet() {
//
//		DogVO dog = mapper.get(1L);
//
//		System.out.println("get 시작");
//		
//		log.info(dog);
//		log.warn(dog);
//		
//		System.out.println("get 끝!");
//		
//	}

	@Test
	public void testUpdate() throws ParseException {
		DogVO dog = mapper.get(4L);

		System.out.println("11111111111111111111111111111111");
		log.info(dog);
		
		SimpleDateFormat date1 = new SimpleDateFormat("yyyy-MM-dd");
		Date date = date1.parse("2000-04-01");
		
		System.out.println("update 시작");
		
		dog.setDogName("수정멍1");
		dog.setDogKind("수정개");
		dog.setDogBirth(date);
		dog.setDogDescription("꼬리 치며 반갑다고 멍멍멍");
		dog.setDogFavorite("Update 테스트 전송");
		dog.setDogPhotoPath("c:/temp/temp3.jpg");
		
		log.info(dog);
				
		mapper.update(dog);
		
		System.out.println("update 끝!!!");
		
	}

//	@Test
	public void testDelete() {
		fail("Not yet implemented");
	}

//	@Test
	public void testGetList() {
		fail("Not yet implemented");
	}

}
