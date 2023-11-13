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


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RootConfig.class })
@Log4j
@WebAppConfiguration

public class PlaceMapperTest {

    @Autowired
    PlaceMapper placeMapper;

    @Test
    public void create() {
        PlaceVO place = new PlaceVO(1L, "new place name", "new place spot", 1L);
        log.info(place);
        placeMapper.create(place);
    }

    @Test
    public void get() {
        PlaceVO place = placeMapper.get(1L);
        log.info(place);
    }

    @Test
    public void update() {
        PlaceVO place = placeMapper.get(2L);
        place.updatePlace("updated place name", "updated place spot", 2L);
        log.info(place);
        placeMapper.update(place);
    }

    @Test
    public void delete() {
       if(placeMapper.delete(1L)){
            log.info("삭제 성공");
        }
       else{
           log.info("삭제 실패");
       }
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
        PlaceVO place = PlaceVO.builder().placeName("new place name2").placeSpot("new place spot2").placeTypeId(2L).build();
        log.info(place);
        placeMapper.insertSelectKey(place);
    }
}