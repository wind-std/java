package com.example.warehouse.controller;

import com.example.warehouse.pojo.Login;
import com.example.warehouse.pojo.Result;
import com.example.warehouse.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private LoginService loginService;
    @PostMapping("/get")
    public Result getuser(@RequestBody Login login) {
        Login H= loginService.getuser(login);
        if (H != null) {
            return Result.success(H);
        } else {
            return Result.error("未查询到相关信息");
        }
    }
}
