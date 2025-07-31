package com.example.familyaccounting.service.impl;

import com.example.familyaccounting.entity.AccountingRecord;
import com.example.familyaccounting.entity.User;
import com.example.familyaccounting.mapper.AccountingRecordMapper;
import com.example.familyaccounting.service.AccountingRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AccountingRecordServiceImpl implements AccountingRecordService {

    @Autowired
    private AccountingRecordMapper recordMapper;

    @Autowired
    private UserServiceImpl userService;

    @Override
    public boolean addRecord(AccountingRecord record) {
        return recordMapper.insert(record) > 0;
    }

    @Override
    public boolean deleteRecord(Integer id, Integer userId) {
        // 检查当前用户是否有权限删除（只有管理员可以删除）
        User user = userService.getUserById(userId);
        if (!"ADMIN".equals(user.getRole())) {
            return false;
        }
        return recordMapper.delete(id) > 0;
    }

    @Override
    public boolean updateRecord(AccountingRecord record) {
        // 检查当前用户是否有权限修改（只有管理员可以修改）
        User user = userService.getUserById(record.getUserId());
        if (!"ADMIN".equals(user.getRole())) {
            return false;
        }
        return recordMapper.update(record) > 0;
    }

    @Override
    public AccountingRecord getRecordById(Integer id) {
        return recordMapper.selectById(id);
    }

    @Override
    public List<AccountingRecord> getRecordsByYear(Integer familyId, Integer year) {
        return recordMapper.selectByYear(familyId, year);
    }

    @Override
    public List<AccountingRecord> getRecordsByMonth(Integer familyId, Integer year, Integer month) {
        return recordMapper.selectByMonth(familyId, year, month);
    }

    @Override
    public List<AccountingRecord> getRecordsByDate(Integer familyId, Date date) {
        return recordMapper.selectByDate(familyId, date);
    }

    @Override
    public List<AccountingRecord> getYearSummary(Integer familyId, Integer year) {
        return recordMapper.summaryByYear(familyId, year);
    }

    @Override
    public List<AccountingRecord> getMonthSummary(Integer familyId, Integer year, Integer month) {
        return recordMapper.summaryByMonth(familyId, year, month);
    }
}
