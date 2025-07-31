package com.example.familyaccounting.entity;

import lombok.Data;
import java.util.Date;

@Data
public class AccountingRecord {
    private Integer id;
    private Integer userId;
    private Integer familyId;
    private String type; // INCOME or EXPENSE
    private Double amount;
    private String category;
    private String description;
    private Date recordDate;
    private Date createTime;
    private Date updateTime;

    // 关联查询字段
    private String username;
    private String nickname;
}
