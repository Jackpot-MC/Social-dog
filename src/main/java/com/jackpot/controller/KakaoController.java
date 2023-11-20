package com.jackpot.controller;

import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;


@Controller
@RequestMapping("/oauth2/kakao")
@Log4j
public class KakaoController {

    //redirect uri에 전달된 코드값으로 access token 요청
    @GetMapping("/")
    public String getAccessToken(@RequestParam("code") String code){

        //토큰확인
        System.out.println("code = " + code);

        //1. header 생성
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded;charset=utf-8");
        //HttpHeaders.CONTENT_TYPE: The HTTP Content-Type header field name.

        //2. body 생성
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        //MutiValueMap<> : value값이 여러개 들어갈수 있다.
        params.add("grant_type", "authorization_code");//고정값
        params.add("client_id", "REST_API_KEY 입력");
        params.add("redirect_uri", "http://localhost:8080/oauth2/kakao");//kakao developer에 등록한 uri
        params.add("redirect_uri", "http://localhost:7777/oauth2/kakao");//{redirect_uri=["http://localhost:8080/oauth2/kakao", "http://localhost:7777/oauth2/kakao"]}
        params.add("code", code);

        //3 header와 body 합치기
        HttpEntity<MultiValueMap<String,String>> httpEntity = new HttpEntity<>(params, httpHeaders);

        //4. http 요청하기
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Object> response = restTemplate.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                httpEntity,
                Object.class
        );

        System.out.println("response = " + response);

        return "/home";
    }
}
