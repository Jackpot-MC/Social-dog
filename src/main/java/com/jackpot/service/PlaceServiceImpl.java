package com.jackpot.service;

import com.jackpot.domain.PlaceVO;
import com.jackpot.mapper.PlaceMapper;

import java.util.List;

public class PlaceServiceImpl implements PlaceService{

    PlaceMapper placeMapper;

    @Override
    public void create(PlaceVO place) {
        placeMapper.insertSelectKey(place);
        Long placeID = place.getPlaceID();
    }

    @Override
    public PlaceVO get(Long placeID) {
        PlaceVO place = placeMapper.get(placeID);
        return place;
    }

    @Override
    public boolean update(PlaceVO place) {
        int result = placeMapper.update(place);
        Long placeID = place.getPlaceID();
        return result == 1;
    }

    @Override
    public boolean delete(Long placeID) {
        return placeMapper.delete(placeID);
    }

    @Override
    public List<PlaceVO> getList() throws Exception {
        return placeMapper.getList();
    }
}
