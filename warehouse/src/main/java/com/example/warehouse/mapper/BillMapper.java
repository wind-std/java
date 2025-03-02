package com.example.warehouse.mapper;

import com.example.warehouse.pojo.Bill;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BillMapper {
    @Insert("insert into warehouse.bill(id, goodid, name, standards, material, supplier, brand, category, operate, unit, warehouse) VALUES (#{id},#{goodid},#{name},#{standards},#{material},#{supplier},#{brand},#{category},#{operate},#{unit},#{warehouse})")
    void add(Bill bill);
}
