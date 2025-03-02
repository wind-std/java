package com.example.elder.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Assessment {
    private String id;
    private String name;
    private Integer score1;
    private Integer rank1;
    private Integer score2;
    private Integer rank2;
    private Integer score3;
    private Integer rank3;
    private Integer score4;
    private Integer rank4;
    private Integer rank;

}
