package com.jackpot.service;

import java.io.IOException;
import java.util.List;

import com.jackpot.domain.AppointmentCriteria;
import com.jackpot.domain.AppointmentVO;
import com.jackpot.domain.DogVO;
import com.jackpot.domain.ParticipantVO;
import com.jackpot.domain.PlaceVO;

public interface AppointmentService {
	public AppointmentVO get(Long appointmentId);
	
	public void register(AppointmentVO appointment) throws IOException;
	
	public void registerPlace(PlaceVO place) throws IOException;
	
	public boolean modify(AppointmentVO appointment) throws IOException;

	public boolean remove(Long appointmentId);
	
	public List<AppointmentVO> getList(AppointmentCriteria cri);	
	
	public int getTotal(AppointmentCriteria cri);

	public List<ParticipantVO> getParticipantList(Long appointmentId);
	
	public Long getMemberId(String loginId);
	
	public void attend(Long appointmentId, Long memberId);
	
	public boolean absent(Long appointmentId, Long memberId);
	
	public int checkAttendance(Long appointmentId, Long memberId);
	
	public List<AppointmentVO> getMyList(Long memberId);
	
	public List<DogVO> getDogList();
}
