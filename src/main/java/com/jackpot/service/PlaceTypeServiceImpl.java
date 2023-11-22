package com.jackpot.service;

import com.jackpot.domain.PlaceTypeVO;
import com.jackpot.mapper.PlaceTypeMapper;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j
public class PlaceTypeServiceImpl implements PlaceTypeService{

    private PlaceTypeMapper placeTypeMapper;

    @Override
    public void create(PlaceTypeVO placeType) {
        placeTypeMapper.insertSelectKey(placeType);
        Long placeTypeID = placeType.getPlaceTypeId();
    }

    @Override
    public PlaceTypeVO get(Long placeTypeID) {
        PlaceTypeVO placeType = placeTypeMapper.get(placeTypeID);
        return placeType;
    }

    @Override
    public boolean update(PlaceTypeVO placeType) {
        int result = placeTypeMapper.update(placeType);

        Long placeTypleID = placeType.getPlaceTypeId();

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
