package com.example.warehouse.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bill {
    private String id;
    private String goodid;
    private String name;
    private String standards;
    private String material;
    private String supplier;
    private String brand;
    private String category;
    private String operate;
    private String unit;
    private String warehouse;
}
