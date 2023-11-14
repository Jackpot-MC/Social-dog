package com.jackpot.service;

import com.jackpot.domain.PlaceTypeVO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PlaceTypeService {

    public void create(PlaceTypeVO placeType);

    public PlaceTypeVO get(Long placeTypeId);

    public boolean update(PlaceTypeVO placeType);

    public boolean delete(Long placeTypeId);

    public List<PlaceTypeVO> getList();


}
