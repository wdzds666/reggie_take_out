package com.nnxy.reggie.dto;

import com.nnxy.reggie.entity.Dish;
import com.nnxy.reggie.entity.DishFlavor;
import com.nnxy.reggie.entity.Dish;
import com.nnxy.reggie.entity.DishFlavor;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class DishDto extends Dish {

    private List<DishFlavor> flavors = new ArrayList<>();

    private String categoryName;

    private Integer copies;
}
