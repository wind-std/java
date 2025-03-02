package com.example.elder.service.impl;

import com.example.elder.mapper.AssessmentMapper;
import com.example.elder.pojo.Assessment;
import com.example.elder.service.AssessmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssessmentServiceImpl implements AssessmentService {
    @Autowired
    private AssessmentMapper assessmentMapper;
    @Override
    public void add_1(Assessment assessment) {
        assessmentMapper.add_1(assessment);
    }

    @Override
    public void add_2(Assessment assessment) {
        assessmentMapper.add_2(assessment);
    }

    @Override
    public void add_3(Assessment assessment) {
        assessmentMapper.add_3(assessment);
    }

    @Override
    public void add_4(Assessment assessment) {
        assessmentMapper.add_4(assessment);
    }

    @Override
    public Assessment get(Assessment assessment) {
        return assessmentMapper.get(assessment);
    }

    @Override
    public void add(Assessment assessment) {
        assessmentMapper.add(assessment);
    }

    @Override
    public List<Assessment> getall() {
        return assessmentMapper.getall();
    }
}
