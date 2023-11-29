package com.jackpot.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jackpot.domain.AppointmentCriteria;
import com.jackpot.domain.AppointmentVO;
import com.jackpot.domain.ParticipantVO;
import com.jackpot.mapper.AppointmentMapper;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
@Transactional(rollbackFor = Exception.class)
public class AppointmentServiceImpl implements AppointmentService {

	@Autowired
	AppointmentMapper mapper;
	
	@Override
	public AppointmentVO get(Long appointmentId) {
		return mapper.read(appointmentId);
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void register(AppointmentVO appointment) throws IOException {
		log.info("insert: " + appointment);
		mapper.insert(appointment);
	}

	@Override
	public boolean modify(AppointmentVO appointment) throws IOException {
		log.info("update: " + appointment);
		int result = mapper.update(appointment);	
		return result == 1;
	}

	@Override
	public boolean remove(Long appointmentId) {
		log.info("delete: " + appointmentId);
		return mapper.delete(appointmentId);
	}

	@Override
	public List<AppointmentVO> getList(AppointmentCriteria cri) {
		log.info("getListWithPaging: " + cri);
		
		return mapper.getListWithPaging(cri);
	}

	@Override
	public int getTotal(AppointmentCriteria cri) {
		log.info("getTotalCount");
		return mapper.getTotalCount(cri);
	}

	@Override
	public List<ParticipantVO> getParticipantList(Long appointmentId) {
		log.info("getParticipantList");
		return mapper.getParticipantList(appointmentId);
	}

	@Override
	public void attend(Long appointmentId, Long memberId) {
		log.info(memberId + "attends " + appointmentId);
		mapper.attend(appointmentId, memberId);
	}

	@Transactional(rollbackFor = Exception.class)	
	@Override
	public boolean absent(Long appointmentId, Long memberId) {
		log.info(memberId + "absent from " + appointmentId);
		return mapper.absent(appointmentId, memberId);
	}

	@Override
	public Long getMemberId(String loginId) {
		log.info("getMemberId: " + loginId);
		return mapper.getMemberId(loginId);
	}
	
}
