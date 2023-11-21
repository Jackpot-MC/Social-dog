package com.jackpot.service;

import java.util.List;

import com.jackpot.domain.AppointmentVO;
import com.jackpot.mapper.AppointmentMapper;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j
public class AppointmentServiceImpl implements AppointmentService {

	@Autowired
	AppointmentMapper appointmentMapper;
	
	@Override
	public void create(AppointmentVO appointment) {
		appointmentMapper.insertSelectKey(appointment);
		Long appointmentId = appointment.getAppointmentId();
	}

	@Override
	public AppointmentVO get(Long appointmentId) {
		AppointmentVO appointment = appointmentMapper.get(appointmentId);
		return appointment;
	}

	@Override
	public boolean update(AppointmentVO appointment) {
		int result = appointmentMapper.update(appointment);
		Long appointmentId = appointment.getAppointmentId();
		return result == 1;
	}

	@Override
	public boolean delete(Long appointmentId) {
		return appointmentMapper.delete(appointmentId);
	}

	@Override
	public List<AppointmentVO> getList() {
		return appointmentMapper.getList();
	}

}
