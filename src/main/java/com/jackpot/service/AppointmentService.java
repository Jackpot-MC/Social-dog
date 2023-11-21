package com.jackpot.service;

import java.io.IOException;
import java.util.List;

import com.jackpot.domain.AppointmentCriteria;
import com.jackpot.domain.AppointmentVO;
import com.jackpot.domain.MemberVO;

public interface AppointmentService {
	public AppointmentVO get(Long appointmentId);
	
	public void register(AppointmentVO appointment) throws IOException;
	
	public boolean modify(AppointmentVO appointment) throws IOException;

	public boolean remove(Long appointmentId);
	
	public List<AppointmentVO> getList(AppointmentCriteria cri);	
	
	public int getTotal(AppointmentCriteria cri);
	
	public List<MemberVO> getMemberLoginId(MemberVO admin);
}
