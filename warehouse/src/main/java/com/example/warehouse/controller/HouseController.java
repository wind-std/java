package com.example.warehouse.controller;

import com.example.warehouse.pojo.House;
import com.example.warehouse.pojo.Result;
import com.example.warehouse.service.HouseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/house")
public class HouseController {
    @Autowired
    private HouseService houseService;
    @PostMapping("/add")
    public Result add(@RequestBody House house){
        log.info("添加仓库：{}",house);
        houseService.add(house);
        return Result.success(house);
    }
    @GetMapping("/getAll")
    public Result getall(){
        List<House> houses=houseService.getall();
        return Result.success(houses);
    }
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable String id){
        log.info("删除仓库:{}",id);
        houseService.delete(id);
        return Result.success(id);
    }
    @PutMapping("/update")
    public Result update(@RequestBody House house){
        log.info("修改仓库:{}",house);
        houseService.update(house);
        return Result.success(house);
    }
    @PutMapping("/put")
    public Result put(@RequestBody House house){
        log.info("修改仓库:{}",house);
        houseService.put(house);
        return Result.success(house);
    }

}
