package com.jackpot.mapper;

import com.jackpot.config.RootConfig;
import com.jackpot.domain.PlaceTypeVO;
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
class PlaceTypeMapperTest {

    @Autowired
    PlaceTypeMapper placeTypeMapper;

    @Test
    public void create() {
        PlaceTypeVO placeType = new PlaceTypeVO();
        placeType.setPlaceTypeId(1L);
        placeType.setPlaceTypeName("새 타입명");
        placeTypeMapper.create(placeType);
    }

    @Test
    public void get() {
        PlaceTypeVO placeType = placeTypeMapper.get(1L);
        log.info(placeType);
    }

    @Test
    public void update() {
        PlaceTypeVO placeTypeVO = placeTypeMapper.get(1L);
        log.info(placeTypeVO);

        placeTypeVO.setPlaceTypeName("수정한 타입명");
        log.info(placeTypeVO);

        placeTypeMapper.update(placeTypeVO);
    }

    @Test
    public void delete() {
        PlaceTypeVO placeTypeVO = placeTypeMapper.get(1L);
        log.info(placeTypeVO);
        placeTypeMapper.delete(1L);
    }

    @Test
    public void getList() {
        List<PlaceTypeVO> placeTypes = placeTypeMapper.getList();
        for(PlaceTypeVO placeType : placeTypes){
            log.info(placeType);
        }
    }

    @Test
    public void insertSelectKey() {
        PlaceTypeVO placeTypeVO = new PlaceTypeVO();

        placeTypeVO.setPlaceTypeName("새 타입명2");
        log.info(placeTypeVO);

        placeTypeMapper.insertSelectKey(placeTypeVO);
    }
}