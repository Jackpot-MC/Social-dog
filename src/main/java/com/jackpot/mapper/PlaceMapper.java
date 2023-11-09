package com.jackpot.mapper;

import com.jackpot.domain.PlaceVO;

import java.util.List;

public interface PlaceMapper {
    public void create(PlaceVO place);

    public PlaceVO get(Long placeID);

    public int update(PlaceVO place);

    public boolean delete(Long placeID);

    public List<PlaceVO> getList() throws Exception;

    public void insertSelectKey(PlaceVO place);
}
