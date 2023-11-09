package com.jackpot.service;

import com.jackpot.domain.PlaceVO;

import java.util.List;

public interface PlaceService {
    public void create(PlaceVO place);

    public PlaceVO get(Long placeID);

    public boolean update(PlaceVO place);

    public boolean delete(Long placeID);

    public List<PlaceVO> getList() throws Exception;
}
