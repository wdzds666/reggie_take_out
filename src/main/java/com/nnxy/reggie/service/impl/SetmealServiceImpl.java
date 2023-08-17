package com.nnxy.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nnxy.reggie.common.CustomException;
import com.nnxy.reggie.dto.SetmealDto;
import com.nnxy.reggie.entity.Dish;
import com.nnxy.reggie.entity.Setmeal;
import com.nnxy.reggie.entity.SetmealDish;
import com.nnxy.reggie.mapper.SetmealMapper;
import com.nnxy.reggie.service.SetmealDishService;
import com.nnxy.reggie.service.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService {

    @Autowired
    private SetmealDishService setmealDishService;

   @Transactional
   public void saveWithDish(SetmealDto setmealDto) {
       this.save(setmealDto);
       List<SetmealDish> setmealDishes=setmealDto.getSetmealDishes();
       setmealDishes.stream().map((item)->{
       item.setSetmealId(setmealDto.getId());
       return item;
       }).collect(Collectors.toList());

       setmealDishService.saveBatch(setmealDishes);
    }
    @Transactional
    @Override
    public void removeWithDish(List<Long> ids) {
        LambdaQueryWrapper<Setmeal> queryWrapper=new LambdaQueryWrapper<>();
       queryWrapper.in(Setmeal::getId,ids);
       queryWrapper.eq(Setmeal::getStatus,1);
        int count =this.count(queryWrapper);

        if(count > 0){
            throw new CustomException("套餐正在售卖，不能删除");
        }
        this.removeByIds(ids);
        LambdaQueryWrapper<SetmealDish> LambdaQueryWrapper = new LambdaQueryWrapper<>();
        LambdaQueryWrapper.in(SetmealDish::getSetmealId,ids);
       setmealDishService.remove(LambdaQueryWrapper);
    }
}
