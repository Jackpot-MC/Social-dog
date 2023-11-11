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



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class})
@Log4j
@WebAppConfiguration
public class PlaceTypeMapperTest {

    @Autowired
    private PlaceTypeMapper placeTypeMapper;

    @Test
    public void create() {
        PlaceTypeVO placeType = PlaceTypeVO.builder()
                .placeTypeId(1L)
                .placeTypeName("new type name")
                .build();
        placeTypeMapper.create(placeType);
        log.info(placeType);
    }

    @Test
    public void get() {
        PlaceTypeVO placeType = placeTypeMapper.get(1L);
        log.info(placeType);
    }

    @Test
    public void update() {
        PlaceTypeVO placeTypeVO = placeTypeMapper.get(1L);
        placeTypeVO.updatePlaceTypeName("updated type name");
        log.info(placeTypeVO);

        placeTypeMapper.update(placeTypeVO);
    }

    @Test
    public void delete() {
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
        PlaceTypeVO placeType = PlaceTypeVO.builder().placeTypeName("new Type Name 2").build();
        log.info(placeType);
        placeTypeMapper.insertSelectKey(placeType);
    }
}