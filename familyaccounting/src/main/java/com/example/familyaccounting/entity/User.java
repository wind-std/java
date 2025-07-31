package com.example.familyaccounting.entity;

import lombok.Data;
import java.util.Date;

@Data
public class User {
    private Integer id;
    private String username;
    private String password;
    private String nickname;
    private String role; // ADMIN or MEMBER
    private Integer familyId;
    private String status; // PENDING, APPROVED, REJECTED
    private Integer applyFamilyId; // 申请加入的家庭ID
    private Date createTime;
    private Date updateTime;
}
