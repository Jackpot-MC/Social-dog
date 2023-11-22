package java.com.jackpot.mapper;

import com.jackpot.mapper.AdminMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.jackpot.config.RootConfig;
import com.jackpot.domain.AdminVO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes= {RootConfig.class})
@Log4j
public class AdminMapperTest {

	@Autowired
	private AdminMapper mapper;
	
	@Test
	public void testGet() {
		AdminVO admin = mapper.get("admin1");
		
		log.info(admin);
		log.warn("성공");
	}
	
//	@Test
//	public void testInsert() {
//		AdminVO admin = new AdminVO();
//		admin.setAdminId(4L);
//		admin.setAdminLoginId("admin4");
//		admin.setAdminLoginPwd("admin4");
//		log.info(admin);
//		
//		mapper.insert(admin);
//		
//		log.info(admin);
//	}

//	@Test
//	public void testUpdate() {
//		AdminVO admin = mapper.get("admin1");
//		log.info(admin);
//		
//		if(admin == null) {
//			return;
//		}
//		
//		admin.setAdminLoginPwd("admin111");
//		log.info(admin);
//		
//		mapper.update(admin);
//		
//		log.info(admin);
//	}

//	@Test
//	public void testDelete() {
//		log.info("DELETE COUNT: " + mapper.delete(2L));
//	}
	
//	@Test
//	public void testDeleteLoginId() {
//		log.info("DELETE COUNT: " + mapper.deleteLoginId("admin3"));
//	}


}
