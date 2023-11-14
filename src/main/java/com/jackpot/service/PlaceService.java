package com.jackpot.service;

import com.jackpot.domain.PlaceVO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PlaceService {
    public void create(PlaceVO place);

    public PlaceVO get(Long placeId);

    public boolean update(PlaceVO place);

    public boolean delete(Long placeId);

    public List<PlaceVO> getList();
}
