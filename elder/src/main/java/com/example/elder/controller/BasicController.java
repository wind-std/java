package com.example.elder.controller;

import com.example.elder.pojo.Basic;
import com.example.elder.pojo.Result;
import com.example.elder.service.BasicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/basic")
public class BasicController {
    @Autowired
    private BasicService basicService;
    @PostMapping("/add1")
    public Result add_1(@RequestBody Basic basic){
        log.info("添加表a1:{}",basic);
        basicService.add_1(basic);
        return Result.success(basic);
    }
    @PostMapping("/add2")
    public Result add_2(@RequestBody Basic basic){
        log.info("添加表a2:{}",basic);
        basicService.add_2(basic);
        return Result.success(basic);
    }
    @PostMapping("/add")
    public Result add_pr(@RequestBody Basic basic){
        log.info("添加表3:{}",basic);
        basicService.add_pr(basic);
        return Result.success(basic);
    }
}
