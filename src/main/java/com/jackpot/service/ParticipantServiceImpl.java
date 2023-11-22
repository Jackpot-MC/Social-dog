package com.jackpot.service;

import java.util.List;

import com.jackpot.domain.ParticipantVO;
import com.jackpot.mapper.ParticipantMapper;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j
public class ParticipantServiceImpl implements ParticipantService {

	@Autowired
	ParticipantMapper participantMapper;
	
	@Override
	public void create(ParticipantVO participant) {
		participantMapper.insertSelectKey(participant);
		Long participantId = participant.getParticipantId();
	}

	@Override
	public ParticipantVO get(Long participantId) {
		ParticipantVO participant = participantMapper.get(participantId);
		return participant;
	}

	@Override
	public boolean delete(Long participantId) {
		return participantMapper.delete(participantId);
	}

	@Override
	public List<ParticipantVO> getList() {
		return participantMapper.getList();
	}

}
