package com.jackpot.mapper;

import java.util.List;

import com.jackpot.domain.AppointmentCriteria;
import com.jackpot.domain.AppointmentVO;
import com.jackpot.domain.MemberVO;

public interface AppointmentMapper {
	public void insert(AppointmentVO appointment);

	public AppointmentVO read(Long appointmentId);

	public int update(AppointmentVO appointment);
	
	public boolean delete(Long appointmentId);

	public List<AppointmentVO> getList();
	
	public List<AppointmentVO> getListWithPaging(AppointmentCriteria cri);
	
	public int getTotalCount(AppointmentCriteria cri);

	public List<MemberVO> getMemberLoginIdMapper(MemberVO member);

}
