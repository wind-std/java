package com.example.warehouse.service.impl;

import com.example.warehouse.mapper.BillMapper;
import com.example.warehouse.pojo.Bill;
import com.example.warehouse.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillServiceImpl implements BillService {
    @Autowired
    private BillMapper billMapper;
    @Override
    public void add(Bill bill) {
        billMapper.add(bill);
    }
}
