package com.jackpot.service;

import java.util.List;

import com.jackpot.domain.ParticipantVO;

public interface ParticipantService {

	public void create(ParticipantVO participant);

	public ParticipantVO get(Long participantId);

	public boolean delete(Long participantId);

	public List<ParticipantVO> getList();

}
