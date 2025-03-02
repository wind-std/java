package com.example.warehouse.service;

import com.example.warehouse.pojo.House;

import java.util.List;

public interface HouseService {
    void add(House house);

    List<House> getall();

    void delete(String id);

    void update(House house);

    void put(House house);
}
