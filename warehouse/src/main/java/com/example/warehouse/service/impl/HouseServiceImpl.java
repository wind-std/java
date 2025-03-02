package com.example.warehouse.service.impl;

import com.example.warehouse.mapper.HouseMapper;
import com.example.warehouse.pojo.House;
import com.example.warehouse.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseServiceImpl implements HouseService {
    @Autowired
    private HouseMapper houseMapper;
    @Override
    public void add(House house) {
        houseMapper.add(house);
    }

    @Override
    public List<House> getall() {
        return houseMapper.getall();
    }

    @Override
    public void delete(String id) {
        houseMapper.delete(id);
    }

    @Override
    public void update(House house) {
        houseMapper.update(house);
    }

    @Override
    public void put(House house) {
        houseMapper.put(house);
    }
}
