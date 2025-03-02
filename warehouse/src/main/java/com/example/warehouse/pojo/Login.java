package com.example.warehouse.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.util.pattern.PathPattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Login {
    private String name;
    private String id;
    private String password;
    private String role;
}
