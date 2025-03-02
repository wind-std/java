package com.example.warehouse.mapper;

import com.example.warehouse.pojo.Login;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LoginMapper {
    @Select("select * from warehouse.login where id=#{id} and password=#{password}")
    Login get(Login login);
}
