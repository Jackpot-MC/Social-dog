package com.jackpot.mapper;

import java.util.List;

import com.jackpot.domain.ParticipantVO;

public interface ParticipantMapper {

	public void create(ParticipantVO participant);

	public ParticipantVO get(Long participantId);

	public boolean delete(Long participantId);

	public List<ParticipantVO> getList();

	public void insertSelectKey(ParticipantVO participant);

}
