package com.jackpot.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PlaceVO {
    private Long placeId;
    private String placeName;
    private String placeSpot;
    private Long placeTypeId;
    private Long placeKakaoId;

    public void updatePlace(String placeName, String placeSpot, Long placeTypeId){
        this.placeName = placeName;
        this.placeSpot = placeSpot;
        this.placeTypeId = placeTypeId;
    }
}
