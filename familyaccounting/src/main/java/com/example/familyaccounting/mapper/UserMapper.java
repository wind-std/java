package com.example.familyaccounting.mapper;

import com.example.familyaccounting.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Insert("INSERT INTO \"USER\" (username, password, nickname, role, family_id,status) " +
            "VALUES (#{username}, #{password}, #{nickname}, #{role}, #{familyId},#{status})")
    @SelectKey(statement = "SELECT IDENT_CURRENT('USER')", keyProperty = "id",
            resultType = Integer.class, before = false)
    int insert(User user);

    @Select("SELECT * FROM \"USER\" WHERE id = #{id}")
    User selectById(Integer id);

    @Select("SELECT * FROM \"USER\" WHERE username = #{username}")
    User selectByUsername(String username);

    @Select("SELECT * FROM \"USER\" WHERE family_id = #{familyId} AND status = 'APPROVED' ")
    List<User> selectByFamilyId(Integer familyId);

    @Update("UPDATE \"USER\" SET password = #{password}, nickname = #{nickname} ,status =#{status} , update_time=NOW() WHERE id = #{id}")
    int update(User user);

    @Update("UPDATE \"USER\" SET password = '123456' WHERE id = #{id}")
    int resetPassword(Integer id);

    @Select("SELECT * FROM \"USER\" WHERE family_id = #{familyId} AND status = #{status}")
    List<User> selectByFamilyIdAndStatus(@Param("familyId") Integer familyId,
                                         @Param("status") String status);
}
