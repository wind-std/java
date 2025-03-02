package com.example.warehouse.mapper;

import com.example.warehouse.pojo.House;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface HouseMapper {
    @Insert("insert into warehouse.house (id, name,thing,num) VALUES (#{id},#{name},#{thing},#{num})")
    void add(House house);
    @Select("select * from warehouse.house")
    List<House> getall();

    @Delete("delete from warehouse.house where id=#{id}")
    void delete(String id);

    @Update("update warehouse.house set name=#{name} where id=#{id}")
    void update(House house);

    @Update("update warehouse.house set num=#{num} where name=#{name}")
    void put(House house);
}
