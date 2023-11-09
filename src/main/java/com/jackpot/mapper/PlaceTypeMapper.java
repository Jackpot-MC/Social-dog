package com.jackpot.mapper;

import com.jackpot.domain.PlaceTypeVO;

import java.util.List;

public interface PlaceTypeMapper {

    public void create(PlaceTypeVO placeType);

    public PlaceTypeVO get(Long placeTypeID);

    public int update(PlaceTypeVO placeType);

    public boolean delete(Long placeTypeID);

    public List<PlaceTypeVO> getList();

    public void insertSelectKey(PlaceTypeVO placeType);
}
