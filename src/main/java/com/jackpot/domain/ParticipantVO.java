package com.jackpot.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParticipantVO {
	private Long participantId;
	private Long appointmentId;

	private String memberName;
	private String memberAddress;
}
