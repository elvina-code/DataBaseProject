package com.devxschool.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class FoodResponse {
    private List<FoodRequest> foodCached;
}
