package com.example.elder.mapper;

import com.example.elder.pojo.Assessment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface AssessmentMapper {
    @Insert("insert into elder.assessment (id, name, score1,rank1) values (#{id},#{name},#{score1},#{rank1})")
    void add_1(Assessment assessment);

    @Update("update elder.assessment set score2=#{score2} , rank2=#{rank2} where id=#{id}")
    void add_2(Assessment assessment);

    @Update("update elder.assessment set score3=#{score3} , rank3=#{rank3} where id=#{id}")
    void add_3(Assessment assessment);

    @Update("update elder.assessment set score4=#{score4} , rank4=#{rank4} where id=#{id}")
    void add_4(Assessment assessment);

    @Select("select * from elder.assessment where id=#{id}")
    Assessment get(Assessment assessment);

    @Update("update elder.assessment set `rank`=#{rank} where id=#{id}")
    void add(Assessment assessment);

    @Select("select * from elder.assessment")
    List<Assessment> getall();
}
