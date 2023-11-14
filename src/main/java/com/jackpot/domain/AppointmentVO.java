package com.jackpot.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentVO {
	private Long appointmentId;
	private String appointmentName;
	private String appointmentDate;
	private Long placeId;
	private Long hostId;
}
