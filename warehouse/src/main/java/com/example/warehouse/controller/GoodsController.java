package com.example.warehouse.controller;

import com.example.warehouse.pojo.Goods;
import com.example.warehouse.pojo.House;
import com.example.warehouse.pojo.Result;
import com.example.warehouse.service.GoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;
    @PostMapping("/add")
    public Result add(@RequestBody Goods goods){
        log.info("添加物资类别：{}",goods);
        goodsService.add(goods);
        return Result.success(goods);
    }
    @GetMapping("/getAll")
    public Result getall(){
        List<Goods> goods=goodsService.getall();
        return Result.success(goods);
    }
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable String id){
        log.info("删除物资类别:{}",id);
        goodsService.delete(id);
        return Result.success(id);
    }
    @PutMapping("/update")
    public Result update(@RequestBody Goods goods){
        log.info("修改物资类别:{}",goods);
        goodsService.update(goods);
        return Result.success(goods);
    }
}
