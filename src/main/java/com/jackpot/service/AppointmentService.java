package com.jackpot.service;

import java.util.List;

import com.jackpot.domain.AppointmentVO;

public interface AppointmentService {
	public void create(AppointmentVO appointment);
	
	public AppointmentVO get(Long appointmentId);
	
	public boolean update(AppointmentVO appointment);

	public boolean delete(Long appointmentId);
	
	public List<AppointmentVO> getList();
}
