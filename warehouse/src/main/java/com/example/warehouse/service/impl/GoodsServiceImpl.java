package com.example.warehouse.service.impl;

import com.example.warehouse.mapper.GoodsMapper;
import com.example.warehouse.pojo.Goods;
import com.example.warehouse.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public void add(Goods goods) {
        goodsMapper.add(goods);
    }

    @Override
    public List<Goods> getall() {
        return goodsMapper.getall();
    }

    @Override
    public void delete(String id) {
        goodsMapper.delete(id);
    }

    @Override
    public void update(Goods goods) {
        goodsMapper.update(goods);
    }
}
