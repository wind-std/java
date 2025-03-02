package com.example.elder.service;

import com.example.elder.pojo.Assessment;

import java.util.List;

public interface AssessmentService {
    void add_1(Assessment assessment);

    void add_2(Assessment assessment);

    void add_3(Assessment assessment);

    void add_4(Assessment assessment);

    Assessment get(Assessment assessment);

    void add(Assessment assessment);

    List<Assessment> getall();
}
