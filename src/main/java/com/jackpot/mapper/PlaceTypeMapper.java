package com.jackpot.mapper;

import com.jackpot.domain.PlaceTypeVO;

import java.util.List;

public interface PlaceTypeMapper {

    public void create(PlaceTypeVO placeType);

    public PlaceTypeVO get(Long placeTypeId);

    public int update(PlaceTypeVO placeType);

    public boolean delete(Long placeTypeId);

    public List<PlaceTypeVO> getList();

    public void insertSelectKey(PlaceTypeVO placeType);
}
