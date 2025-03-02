package com.example.elder.service.impl;

import com.example.elder.mapper.BasicMapper;
import com.example.elder.pojo.Basic;
import com.example.elder.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BasicServiceImpl implements BasicService {
    @Autowired
    private BasicMapper basicMapper;
    @Override
    public void add_1(Basic basic) {
        basicMapper.add_1(basic);
    }

    @Override
    public void add_2(Basic basic) {
        basicMapper.add_2(basic);
    }

    @Override
    public void add_pr(Basic basic) {
        basicMapper.add_pr(basic);
    }
}
