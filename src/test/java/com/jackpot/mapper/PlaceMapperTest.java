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
@WebAppConfiguration
@ContextConfiguration(classes= {RootConfig.class})
@Log4j
public class PlaceMapperTest {

    @Autowired
    PlaceMapper placeMapper;

    @Test
    public void get(Long placeId){
        System.out.println(1);
        PlaceVO placeVO = placeMapper.get(1L);
        log.info(placeVO);
    }

    @Test
    public void getList() throws Exception {
        List<PlaceVO> places = placeMapper.getList();
        for(PlaceVO p : places){
            log.info(p);
        }
    }
}