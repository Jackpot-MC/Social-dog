package com.jackpot.service;

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
@WebAppConfiguration
@ContextConfiguration(classes= {RootConfig.class})
@Log4j
public class PlaceTypeServiceTest {

    @Autowired
    private PlaceTypeService placeTypeService;

    @Test
    public void create() {
        PlaceTypeVO placeType = PlaceTypeVO.builder().placeTypeId(3L).placeTypeName("new place type name 3").build();
        log.info(placeType);
        placeTypeService.create(placeType);
    }

    @Test
    public void get() {
        PlaceTypeVO placeType = placeTypeService.get(3L);
        log.info(placeType);
    }

    @Test
    public void update() {
        PlaceTypeVO placeType = placeTypeService.get(3L);
        placeType.updatePlaceTypeName("new Place Type name 3");
        log.info("수정된 결과 : " + placeTypeService.update(placeType));
    }

    @Test
    public void delete() {
        if(placeTypeService.delete(3L)){
            log.info("삭제 성공");
        }
        else {
            log.info("삭제 실패");
        }
    }

    @Test
    public void getList() {
        List<PlaceTypeVO> placeTypes = placeTypeService.getList();
        for(PlaceTypeVO placeType : placeTypes){
            log.info(placeType);
        }
    }
}