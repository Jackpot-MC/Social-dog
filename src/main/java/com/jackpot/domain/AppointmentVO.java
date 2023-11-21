package com.jackpot.domain;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentVO {
	private Long appointmentId;
	
	@NotBlank(message = "약속 이름은 필수 항목입니다.")
	private String appointmentName;
	
	private Long placeId;
	private Long hostId;
	private String memberLoginId;
	private String memberLoginPwd;

//	private Date appointmentDate;
	private Date regDate;
	private String placeSpot;
	private String placeTypeName;
}
