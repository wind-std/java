package com.example.familyaccounting;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Connection;
import java.sql.DriverManager;

@SpringBootTest
class FamilyaccountingApplicationTests {

    @Test
    void contextLoads() {
        String url = "jdbc:dm://localhost:5236/FAMILY_ACCOUNTING_SYSTEM";
        String user = "SYSDBA";
        String password = "Zsl15188987568";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("达梦数据库连接成功！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
