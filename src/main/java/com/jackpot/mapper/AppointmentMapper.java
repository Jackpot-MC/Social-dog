package com.jackpot.mapper;

import java.util.List;

import com.jackpot.domain.AppointmentCriteria;
import com.jackpot.domain.AppointmentVO;
import com.jackpot.domain.ParticipantVO;
import com.jackpot.domain.PlaceVO;

public interface AppointmentMapper {
	public void insert(AppointmentVO appointment);
	
	public void insertPlace(PlaceVO place);

	public AppointmentVO read(Long appointmentId);

	public int update(AppointmentVO appointment);
	
	public boolean delete(Long appointmentId);

	public List<AppointmentVO> getList();
	
	public List<AppointmentVO> getListWithPaging(AppointmentCriteria cri);
	
	public int setPlaceIdFromPlaceName(Long appointmentId, String place_name);
	
	public int getTotalCount(AppointmentCriteria cri);
	
	public List<ParticipantVO> getParticipantList(Long appointmentId);
	
	public Long getMemberId(String loginId);
	
	public void attend(Long appointmentId, Long memberId);
	
	public boolean absent(Long participantId, Long memberId);
	
	public int checkAttendance(Long appointmentId, Long memberId);
	
	public List<AppointmentVO> getMyList(Long memberId);
}
