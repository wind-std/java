package com.example.elder.controller;

import com.example.elder.pojo.Assessment;
import com.example.elder.pojo.Result;
import com.example.elder.service.AssessmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/ass")
public class AssessmentController {
    @Autowired
    private AssessmentService assessmentService;
    @PostMapping("/add1")
    public Result add_1(@RequestBody Assessment assessment){
        log.info("添加表b1:{}",assessment);
        assessmentService.add_1(assessment);
        return Result.success(assessment);
    }
    @PutMapping("/add2")
    public Result add_2(@RequestBody Assessment assessment){
        log.info("添加表b2:{}",assessment);
        assessmentService.add_2(assessment);
        return Result.success(assessment);
    }
    @PutMapping("/add3")
    public Result add_3(@RequestBody Assessment assessment){
        log.info("添加表b3:{}",assessment);
        assessmentService.add_3(assessment);
        return Result.success(assessment);
    }
    @PutMapping("/add4")
    public Result add_4(@RequestBody Assessment assessment){
        log.info("添加表b4:{}",assessment);
        assessmentService.add_4(assessment);
        return Result.success(assessment);
    }
    @PostMapping("/get")
    public Result get(@RequestBody Assessment assessment) {
        Assessment A= assessmentService.get(assessment);
        if (A != null) {
            return Result.success(A);
        } else {
            return Result.error("未查询到相关信息");
        }
    }
    @PutMapping("/add")
    public Result ability(@RequestBody Assessment assessment){
        log.info("添加能力等级:{}",assessment);
        assessmentService.add(assessment);
        return Result.success(assessment);
    }
    @GetMapping("/getAll")
    public Result getall(){
        List<Assessment> employees=assessmentService.getall();
        return Result.success(employees);
    }
}
