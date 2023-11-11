package com.jackpot.service;

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
@WebAppConfiguration
@ContextConfiguration(classes = {RootConfig.class})
@Log4j
public class PlaceServiceTest {

    @Autowired
    private PlaceService placeService;

    @Test
    public void create() {
        PlaceVO place = PlaceVO.builder()
                .placeId(3L)
                .placeName("new place name3")
                .placeSpot("new place spot3")
                .placeTypeID(3L)
                .build();

        log.info(place);
        placeService.create(place);
    }

    @Test
    public void get() {
        PlaceVO place = placeService.get(1L);
        log.info(place);
    }

    @Test
    public void update() {
        PlaceVO place = placeService.get(1L);
        place.updatePlace("updated place name2", "updated place spot2", 4L);
        log.info("수정 : " + placeService.update(place));
    }

    @Test
    public void delete() {
        if (placeService.delete(3L)) {
            log.info("삭제 성공");
        } else {
            log.info("삭제 실패");
        }
    }

    @Test
    public void getList() {
        List<PlaceVO> places = placeService.getList();
        for (PlaceVO place : places) {
            log.info(place);
        }
    }
}