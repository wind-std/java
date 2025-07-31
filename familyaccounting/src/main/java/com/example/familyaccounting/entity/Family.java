package com.example.familyaccounting.entity;

import lombok.Data;
import java.util.Date;

@Data
public class Family {
    private Integer id;
    private String name;
    private Date createTime;
    private Date updateTime;
    private Integer creatorId;
    private String description;
}
