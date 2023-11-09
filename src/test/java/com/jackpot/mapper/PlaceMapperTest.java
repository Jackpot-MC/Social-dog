package com.jackpot.mapper;

import com.jackpot.config.RootConfig;
import com.jackpot.domain.PlaceVO;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RootConfig.class })
@Log4j
@WebAppConfiguration
public class PlaceMapperTest {

    @Autowired
    PlaceMapper placeMapper;

    @Test
    public void create() {
        PlaceVO place = new PlaceVO();

        place.setPlaceId(1L);
        place.setPlaceName("new name");
        place.setPlaceSpot("new spot");
        place.setPlaceTypeId(1L);

        placeMapper.create(place);
    }

    @Test
    public void get() {
        PlaceVO place = placeMapper.get(1L);
        log.info(place);
    }

    @Test
    public void update() {
        PlaceVO place = placeMapper.get(1L);
        place.setPlaceSpot("수정된 spot");
        place.setPlaceName("updated name");
        place.setPlaceTypeId(2L);

        log.info(place);
        placeMapper.update(place);
    }

    @Test
    public void delete() {
        placeMapper.delete(1L);
    }

    @Test
    public void getList() {
        List<PlaceVO> places = placeMapper.getList();
        for(PlaceVO place : places){
            log.info(place);
        }
    }

    @Test
    public void insertSelectKey() {
        PlaceVO place = new PlaceVO();
        place.setPlaceTypeId(2L);
        place.setPlaceSpot("new spot2");
        place.setPlaceName("new name2");
        log.info("새 객체 : "+place);

        placeMapper.insertSelectKey(place);
    }
}