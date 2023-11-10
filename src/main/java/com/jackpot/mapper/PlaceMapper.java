package com.jackpot.mapper;

import com.jackpot.domain.PlaceVO;

import java.util.List;

public interface PlaceMapper {
    public void create(PlaceVO place);

    public PlaceVO get(Long placeId);

    public int update(PlaceVO place);

    public boolean delete(Long placeId);

    public List<PlaceVO> getList();

    public void insertSelectKey(PlaceVO place);
}
