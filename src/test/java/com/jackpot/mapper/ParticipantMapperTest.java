package com.jackpot.mapper;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.jackpot.config.RootConfig;
import com.jackpot.domain.ParticipantVO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes= {RootConfig.class})
@Log4j
public class ParticipantMapperTest {

	@Autowired
	ParticipantMapper participantMapper;
	
//	@Test
	public void testCreate() {
		System.out.println("testCreate()");
		ParticipantVO participant = new ParticipantVO(); 
		participant.setMemberId(2L);
		participant.setAppointmentId(1L);
		
		participantMapper.insertSelectKey(participant);
	}

//	@Test
	public void testGet() {
		System.out.println("testGet()");
		ParticipantVO participant = participantMapper.get(1L);
		log.info(participant);
	}

//	@Test
	public void testDelete() {
		System.out.println("testDelete()");
		boolean check = participantMapper.delete(11L);
		System.out.println("'Is it gone?': " + check);
	}

	@Test
	public void testGetList() {
		System.out.println("testGetList()");
		List<ParticipantVO> participantList = participantMapper.getList();
		for(ParticipantVO participant : participantList) {
			log.info(participant);
		}
		
	}

//	@Test
	public void testInsertSelectKey() {
		fail("Not yet implemented");
	}

}
