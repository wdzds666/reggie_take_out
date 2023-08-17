package com.nnxy.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nnxy.reggie.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
}
