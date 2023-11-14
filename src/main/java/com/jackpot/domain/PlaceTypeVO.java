package com.jackpot.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PlaceTypeVO {
    private Long placeTypeId;
    private String placeTypeName;

    public void updatePlaceTypeName(String placeTypeName){
        this.placeTypeName = placeTypeName;
    }
}
