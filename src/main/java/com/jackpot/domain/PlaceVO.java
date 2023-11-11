package com.jackpot.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlaceVO {
    private Long placeId;
    private String placeName;
    private String placeSpot;
    private Long placeTypeID;

    public void updatePlace(String placeName, String placeSpot, Long placeTypeID){
        this.placeName = placeName;
        this.placeSpot = placeSpot;
        this.placeTypeID = placeTypeID;
    }
}
