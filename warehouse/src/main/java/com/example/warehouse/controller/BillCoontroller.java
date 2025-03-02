package com.example.warehouse.controller;

import com.example.warehouse.pojo.Bill;
import com.example.warehouse.pojo.Goods;
import com.example.warehouse.pojo.Result;
import com.example.warehouse.service.BillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/bill")
public class BillCoontroller {
    @Autowired
    private BillService billService;
    @PostMapping("/add")
    public Result add(@RequestBody Bill bill){
        log.info("添加物资类别：{}",bill);
        billService.add(bill);
        return Result.success(bill);
    }
}
