package com.jackpot.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jackpot.domain.AppointmentCriteria;
import com.jackpot.domain.AppointmentVO;
import com.jackpot.domain.DogVO;
import com.jackpot.domain.ParticipantVO;
import com.jackpot.domain.PlaceVO;
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
		
		Long appointmentId = appointment.getAppointmentId();
		String placeName = appointment.getPlaceName();
		mapper.setPlaceIdFromPlaceName(appointmentId, placeName);
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void registerPlace(PlaceVO place) throws IOException {
		log.info("insert: " + place);
		mapper.insertPlace(place);
	}

	@Override
	public boolean modify(AppointmentVO appointment) throws IOException {
		log.info("update: " + appointment);
		int result = mapper.update(appointment);	
		
		Long appointmentId = appointment.getAppointmentId();
		String placeName = appointment.getPlaceName();
		mapper.setPlaceIdFromPlaceName(appointmentId, placeName);
		
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

	@Override
	public int checkAttendance(Long appointmentId, Long memberId) {
		log.info("checkAttendance: Is " + memberId + "in " + appointmentId);
		return mapper.checkAttendance(appointmentId, memberId);
	}

	@Override
	public List<AppointmentVO> getMyList(Long memberId) {
		log.info("getMyList: " + memberId);
		return mapper.getMyList(memberId);
	}

	@Override
	public List<DogVO> getDogList() {
		// TODO Auto-generated method stub
		return mapper.getDogList();
	}
	
}
