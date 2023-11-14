package com.jackpot.mapper;

import java.util.List;

import com.jackpot.domain.AppointmentVO;

public interface AppointmentMapper {
	public void create(AppointmentVO appointment);

	public AppointmentVO get(Long appointmentId);

	public int update(AppointmentVO appointment);
	
	public boolean delete(Long appointmentId);

	public List<AppointmentVO> getList();

	public void insertSelectKey(AppointmentVO appointment);
}
