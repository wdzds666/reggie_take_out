package com.nnxy.reggie.dto;

import com.nnxy.reggie.entity.Setmeal;
import com.nnxy.reggie.entity.SetmealDish;
import com.nnxy.reggie.entity.Setmeal;
import com.nnxy.reggie.entity.SetmealDish;
import lombok.Data;
import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
