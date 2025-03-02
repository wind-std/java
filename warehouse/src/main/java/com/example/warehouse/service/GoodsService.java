package com.example.warehouse.service;

import com.example.warehouse.pojo.Goods;

import java.util.List;

public interface GoodsService {
    void add(Goods goods);

    List<Goods> getall();

    void delete(String id);

    void update(Goods goods);
}
