package com.example.warehouse.mapper;

import com.example.warehouse.pojo.Goods;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
public interface GoodsMapper {
    @Insert("insert into warehouse.goods (id, name, standards, material) VALUES (#{id},#{name},#{standards},#{material})")
    void add(Goods goods);

    @Select("select * from warehouse.goods")
    List<Goods> getall();

    @Delete("delete from warehouse.goods where id=#{id}")
    void delete(String id);

    @Update("update warehouse.goods set name=#{name},standards=#{standards},material=#{material}")
    void update(Goods goods);
}
