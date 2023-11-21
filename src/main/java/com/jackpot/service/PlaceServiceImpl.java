package com.jackpot.service;

import com.jackpot.domain.PlaceVO;
import com.jackpot.mapper.PlaceMapper;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j
public class PlaceServiceImpl implements PlaceService{

    @Autowired
    PlaceMapper placeMapper;

    @Override
    public void create(PlaceVO place) {
        placeMapper.insertSelectKey(place);
        Long placeID = place.getPlaceId();
    }

    @Override
    public PlaceVO get(Long placeID) {
        PlaceVO place = placeMapper.get(placeID);
        return place;
    }

    @Override
    public boolean update(PlaceVO place) {
        int result = placeMapper.update(place);
        Long placeID = place.getPlaceId();
        return result == 1;
    }

    @Override
    public boolean delete(Long placeID) {
        return placeMapper.delete(placeID);
    }

    @Override
    public List<PlaceVO> getList(){
        return placeMapper.getList();
    }
}
