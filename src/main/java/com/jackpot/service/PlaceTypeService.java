package com.jackpot.service;

import com.jackpot.domain.PlaceTypeVO;

import java.util.List;

public interface PlaceTypeService {

    public void create(PlaceTypeVO placeType);

    public PlaceTypeVO get(Long placeTypeId);

    public boolean update(PlaceTypeVO placeType);

    public boolean delete(Long placeTypeId);

    public List<PlaceTypeVO> getList();


}
