package com.jackpot.mapper;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.jackpot.config.RootConfig;
import com.jackpot.domain.AppointmentVO;
import com.jackpot.domain.ParticipantVO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes= {RootConfig.class})
@Log4j
public class AppointmentMapperTest {

	@Autowired
	AppointmentMapper appointmentMapper;
	
	@Test
	public void testCreate() {
		System.out.println("testCreate()");
		AppointmentVO appointment = new AppointmentVO();
		appointment.setRegDate(new Date());
		appointment.setAppointmentName("새로 생성한 약속");
		appointment.setHostId(2L);
		appointment.setPlaceId(1L);
		appointmentMapper.insert(appointment);
	}

	@Test
	public void testGet() {
		System.out.println("testGet()");
		AppointmentVO appointment = appointmentMapper.read(5L);
		log.info(appointment);
	}

	@Test
	public void testUpdate() {
		System.out.println("testUpdate()");
		AppointmentVO appointment = new AppointmentVO();
		appointment.setAppointmentId(1L);
		appointment.setRegDate(new Date());
		appointment.setAppointmentName("수정한 약속");
		appointment.setHostId(2L);
		appointment.setPlaceId(1L);
		int chk_success = appointmentMapper.update(appointment);
		log.info(chk_success);
	}

	@Test
	public void testDelete() {
		System.out.println("testDelete()");
		boolean chk_success = appointmentMapper.delete(1L);
		log.info(chk_success);
	}

	@Test
	public void testGetList() {
		System.out.println("testGetList()");
		List<AppointmentVO> appointmentList = appointmentMapper.getList();
		for(AppointmentVO appointment : appointmentList) {
			log.info(appointment);
		}
	}


}
