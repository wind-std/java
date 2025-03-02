package com.example.warehouse.service.impl;

import com.example.warehouse.mapper.LoginMapper;
import com.example.warehouse.pojo.Login;
import com.example.warehouse.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private LoginMapper loginMapper;
    @Override
    public Login getuser(Login login) {
        return loginMapper.get(login);
    }
}
