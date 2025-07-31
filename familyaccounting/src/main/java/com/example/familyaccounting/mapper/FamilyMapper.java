package com.example.familyaccounting.mapper;

import com.example.familyaccounting.entity.Family;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FamilyMapper {
    @Insert("INSERT INTO \"FAMILY\" (name, description) VALUES (#{name}, #{description})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Family family);

    @Update("UPDATE \"FAMILY\" SET creator_id = #{creatorId} WHERE id = #{familyId}")
    void updateCreator(@Param("familyId") Integer familyId, @Param("creatorId") Integer creatorId);

    @Select("SELECT * FROM \"FAMILY\" WHERE id = #{id}")
    Family selectById(Integer id);

    @Select("SELECT * FROM \"FAMILY\" WHERE name = #{name}")
    Family findByName(String name);


    @Select("SELECT * FROM \"FAMILY\"")
    List<Family> selectAllFamilies();
}
