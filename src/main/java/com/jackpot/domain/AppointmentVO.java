package com.jackpot.domain;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

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
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private Date appointmentDate;
	private String content;

	private String placeSpot;
	private String placeName;
	private String placeKakaoId;	
	private String placeTypeName;
	
	private Long participantId;
	private Long memberId;
	private String memberName;
	
	public String getSummary() {
		return content.replace("<p>", "").replace("</p>", " ").replace("<br>", " ");
	}
}