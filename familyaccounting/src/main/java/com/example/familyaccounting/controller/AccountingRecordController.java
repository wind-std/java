package com.example.familyaccounting.controller;

import com.example.familyaccounting.entity.AccountingRecord;
import com.example.familyaccounting.entity.User;
import com.example.familyaccounting.service.AccountingRecordService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 收支记录控制器
 * 处理所有与收支记录相关的操作
 */
@RestController
@RequestMapping("/api/record")
public class AccountingRecordController {

    @Autowired
    private AccountingRecordService recordService;

    /**
     * 添加新的收支记录
     * @param record 收支记录信息
     * @param request HTTP请求对象
     * @return 添加结果
     */
    @PostMapping("/add")
    public ResponseResult<Boolean> addRecord(@RequestBody AccountingRecord record, HttpServletRequest request) {
        // 1. 验证会话和登录状态
        HttpSession session = request.getSession(false);
        if (session == null) {
            return ResponseResult.error("请先登录");
        }

        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            return ResponseResult.error("请先登录");
        }

        // 2. 设置记录的用户ID和家庭ID
        record.setUserId(currentUser.getId());
        record.setFamilyId(currentUser.getFamilyId());

        // 3. 调用服务层添加记录
        if (recordService.addRecord(record)) {
            return ResponseResult.success(true);
        }
        return ResponseResult.error("添加记录失败");
    }

    /**
     * 删除收支记录（仅管理员可用）
     * @param id 记录ID
     * @param request HTTP请求对象
     * @return 删除结果
     */
    @PostMapping("/delete/{id}")
    public ResponseResult<Boolean> deleteRecord(@PathVariable Integer id, HttpServletRequest request) {
        // 1. 验证会话和登录状态
        HttpSession session = request.getSession(false);
        if (session == null) {
            return ResponseResult.error("请先登录");
        }

        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            return ResponseResult.error("请先登录");
        }

        // 2. 调用服务层删除记录（服务层会验证权限）
        if (recordService.deleteRecord(id, currentUser.getId())) {
            return ResponseResult.success(true);
        }
        return ResponseResult.error("删除记录失败，可能没有权限");
    }

    /**
     * 更新收支记录（仅管理员可用）
     * @param record 更新后的记录信息
     * @param request HTTP请求对象
     * @return 更新结果
     */
    @PostMapping("/update")
    public ResponseResult<Boolean> updateRecord(@RequestBody AccountingRecord record, HttpServletRequest request) {
        // 1. 验证会话和登录状态
        HttpSession session = request.getSession(false);
        if (session == null) {
            return ResponseResult.error("请先登录");
        }

        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            return ResponseResult.error("请先登录");
        }

        // 2. 设置操作用户ID
        record.setUserId(currentUser.getId());

        // 3. 调用服务层更新记录（服务层会验证权限）
        if (recordService.updateRecord(record)) {
            return ResponseResult.success(true);
        }
        return ResponseResult.error("更新记录失败，可能没有权限");
    }

    /**
     * 根据ID获取单条记录详情
     * @param id 记录ID
     * @return 记录详情
     */
    @GetMapping("/get/{id}")
    public ResponseResult<AccountingRecord> getRecordById(@PathVariable Integer id) {
        // 1. 调用服务层获取记录
        AccountingRecord record = recordService.getRecordById(id);

        if (record != null) {
            return ResponseResult.success(record);
        }
        return ResponseResult.error("记录不存在");
    }

    /**
     * 按年份查询记录
     * @param year 年份
     * @param request HTTP请求对象
     * @return 该年份的所有记录
     */
    @GetMapping("/year/{year}")
    public ResponseResult<List<AccountingRecord>> getRecordsByYear(@PathVariable Integer year, HttpServletRequest request) {
        // 1. 验证会话和登录状态
        HttpSession session = request.getSession(false);
        if (session == null) {
            return ResponseResult.error("请先登录");
        }

        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            return ResponseResult.error("请先登录");
        }

        // 2. 调用服务层获取年度记录
        List<AccountingRecord> records = recordService.getRecordsByYear(currentUser.getFamilyId(), year);
        return ResponseResult.success(records);
    }

    /**
     * 按年月查询记录
     * @param year 年份
     * @param month 月份
     * @param request HTTP请求对象
     * @return 该年月的所有记录
     */
    @GetMapping("/month/{year}/{month}")
    public ResponseResult<List<AccountingRecord>> getRecordsByMonth(@PathVariable Integer year,
                                                                    @PathVariable Integer month,
                                                                    HttpServletRequest request) {
        // 1. 验证会话和登录状态
        HttpSession session = request.getSession(false);
        if (session == null) {
            return ResponseResult.error("请先登录");
        }

        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            return ResponseResult.error("请先登录");
        }

        // 2. 调用服务层获取月度记录
        List<AccountingRecord> records = recordService.getRecordsByMonth(currentUser.getFamilyId(), year, month);
        return ResponseResult.success(records);
    }

    /**
     * 按日期查询记录
     * @param date 查询日期
     * @param request HTTP请求对象
     * @return 该日期的所有记录
     */
    @GetMapping("/date/{date}")
    public ResponseResult<List<AccountingRecord>> getRecordsByDate(@PathVariable Date date, HttpServletRequest request) {
        // 1. 验证会话和登录状态
        HttpSession session = request.getSession(false);
        if (session == null) {
            return ResponseResult.error("请先登录");
        }

        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            return ResponseResult.error("请先登录");
        }

        // 2. 调用服务层获取日期记录
        List<AccountingRecord> records = recordService.getRecordsByDate(currentUser.getFamilyId(), date);
        return ResponseResult.success(records);
    }

    /**
     * 获取年度收支汇总
     * @param year 年份
     * @param request HTTP请求对象
     * @return 年度汇总数据
     */
    @GetMapping("/summary/year/{year}")
    public ResponseResult<List<AccountingRecord>> getYearSummary(@PathVariable Integer year, HttpServletRequest request) {
        // 1. 验证会话和登录状态
        HttpSession session = request.getSession(false);
        if (session == null) {
            return ResponseResult.error("请先登录");
        }

        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            return ResponseResult.error("请先登录");
        }

        // 2. 调用服务层获取年度汇总
        List<AccountingRecord> summary = recordService.getYearSummary(currentUser.getFamilyId(), year);
        return ResponseResult.success(summary);
    }

    /**
     * 获取月度收支汇总
     * @param year 年份
     * @param month 月份
     * @param request HTTP请求对象
     * @return 月度汇总数据
     */
    @GetMapping("/summary/month/{year}/{month}")
    public ResponseResult<List<AccountingRecord>> getMonthSummary(@PathVariable Integer year,
                                                                  @PathVariable Integer month,
                                                                  HttpServletRequest request) {
        // 1. 验证会话和登录状态
        HttpSession session = request.getSession(false);
        if (session == null) {
            return ResponseResult.error("请先登录");
        }

        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            return ResponseResult.error("请先登录");
        }

        // 2. 调用服务层获取月度汇总
        List<AccountingRecord> summary = recordService.getMonthSummary(currentUser.getFamilyId(), year, month);
        return ResponseResult.success(summary);
    }
}