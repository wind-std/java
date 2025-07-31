package com.example.familyaccounting.dto;

import lombok.Data;

@Data
public class RegisterDTO {
    private String username;
    private String password;
    private String nickname;
    private Integer familyId;
}
