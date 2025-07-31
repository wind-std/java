package com.example.familyaccounting.service;

import com.example.familyaccounting.entity.AccountingRecord;

import java.util.Date;
import java.util.List;

public interface AccountingRecordService {
    boolean addRecord(AccountingRecord record);
    boolean deleteRecord(Integer id, Integer userId);
    boolean updateRecord(AccountingRecord record);
    AccountingRecord getRecordById(Integer id);
    List<AccountingRecord> getRecordsByYear(Integer familyId, Integer year);
    List<AccountingRecord> getRecordsByMonth(Integer familyId, Integer year, Integer month);
    List<AccountingRecord> getRecordsByDate(Integer familyId, Date date);
    List<AccountingRecord> getYearSummary(Integer familyId, Integer year);
    List<AccountingRecord> getMonthSummary(Integer familyId, Integer year, Integer month);
}
