package com.jackpot.service;

import com.jackpot.domain.PlaceTypeVO;
import com.jackpot.mapper.PlaceTypeMapper;

import java.util.List;

public class PlaceTypeServiceImpl implements PlaceTypeService{

    private PlaceTypeMapper placeTypeMapper;

    @Override
    public void create(PlaceTypeVO placeType) {
        placeTypeMapper.insertSelectKey(placeType);
        Long placeTypeID = placeType.getPlaceTypeID();
    }

    @Override
    public PlaceTypeVO get(Long placeTypeID) {
        PlaceTypeVO placeType = placeTypeMapper.get(placeTypeID);
        return placeType;
    }

    @Override
    public boolean update(PlaceTypeVO placeType) {
        int result = placeTypeMapper.update(placeType);

        Long placeTypleID = placeType.getPlaceTypeID();

        return result == 1;
    }

    @Override
    public boolean delete(Long placeTypeID) {
        return placeTypeMapper.delete(placeTypeID);
    }

    @Override
    public List<PlaceTypeVO> getList() {
        return placeTypeMapper.getList();
    }
}
