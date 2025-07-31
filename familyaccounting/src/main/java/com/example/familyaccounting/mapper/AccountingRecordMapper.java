package com.example.familyaccounting.mapper;

import com.example.familyaccounting.entity.AccountingRecord;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
public interface AccountingRecordMapper {
    @Insert("INSERT INTO \"ACCOUNTING_RECORD\" (\"USER_ID\", \"FAMILY_ID\", \"TYPE\", \"AMOUNT\", \"CATEGORY\", \"DESCRIPTION\", \"RECORD_DATE\") " +
            "VALUES (#{userId}, #{familyId}, #{type}, #{amount}, #{category}, #{description}, #{recordDate})")
    @SelectKey(statement = "SELECT IDENT_CURRENT('ACCOUNTING_RECORD')", keyProperty = "id",
            resultType = Integer.class, before = false)
    int insert(AccountingRecord record);

    @Delete("DELETE FROM \"ACCOUNTING_RECORD\" WHERE \"ID\" = #{id}")
    int delete(Integer id);

    @Update("UPDATE \"ACCOUNTING_RECORD\" SET \"TYPE\" = #{type}, \"AMOUNT\" = #{amount}, \"CATEGORY\" = #{category}, " +
            "\"DESCRIPTION\" = #{description}, \"RECORD_DATE\" = #{recordDate} WHERE \"ID\" = #{id}")
    int update(AccountingRecord record);

    @Select("SELECT r.*, u.\"USERNAME\", u.\"NICKNAME\" FROM \"ACCOUNTING_RECORD\" r LEFT JOIN \"USER\" u ON r.\"USER_ID\" = u.\"ID\" " +
            "WHERE r.\"ID\" = #{id}")
    AccountingRecord selectById(Integer id);

    @Select("SELECT r.*, u.\"USERNAME\", u.\"NICKNAME\" FROM \"ACCOUNTING_RECORD\" r LEFT JOIN \"USER\" u ON r.\"USER_ID\" = u.\"ID\" " +
            "WHERE r.\"FAMILY_ID\" = #{familyId} AND EXTRACT(YEAR FROM r.\"RECORD_DATE\") = #{year}")
    List<AccountingRecord> selectByYear(@Param("familyId") Integer familyId, @Param("year") Integer year);

    @Select("SELECT r.*, u.\"USERNAME\", u.\"NICKNAME\" FROM \"ACCOUNTING_RECORD\" r LEFT JOIN \"USER\" u ON r.\"USER_ID\" = u.\"ID\" " +
            "WHERE r.\"FAMILY_ID\" = #{familyId} AND EXTRACT(YEAR FROM r.\"RECORD_DATE\") = #{year} " +
            "AND EXTRACT(MONTH FROM r.\"RECORD_DATE\") = #{month}")
    List<AccountingRecord> selectByMonth(@Param("familyId") Integer familyId,
                                         @Param("year") Integer year,
                                         @Param("month") Integer month);

    @Select("SELECT r.*, u.\"USERNAME\", u.\"NICKNAME\" FROM \"ACCOUNTING_RECORD\" r LEFT JOIN \"USER\" u ON r.\"USER_ID\" = u.\"ID\" " +
            "WHERE r.\"FAMILY_ID\" = #{familyId} AND r.\"RECORD_DATE\" = #{date}")
    List<AccountingRecord> selectByDate(@Param("familyId") Integer familyId, @Param("date") Date date);

    @Select("SELECT \"TYPE\", SUM(\"AMOUNT\") as \"AMOUNT\", \"CATEGORY\" FROM \"ACCOUNTING_RECORD\" " +
            "WHERE \"FAMILY_ID\" = #{familyId} AND EXTRACT(YEAR FROM \"RECORD_DATE\") = #{year} " +
            "GROUP BY \"TYPE\", \"CATEGORY\"")
    List<AccountingRecord> summaryByYear(@Param("familyId") Integer familyId, @Param("year") Integer year);

    @Select("SELECT \"TYPE\", SUM(\"AMOUNT\") as \"AMOUNT\", \"CATEGORY\" FROM \"ACCOUNTING_RECORD\" " +
            "WHERE \"FAMILY_ID\" = #{familyId} AND EXTRACT(YEAR FROM \"RECORD_DATE\") = #{year} " +
            "AND EXTRACT(MONTH FROM \"RECORD_DATE\") = #{month} GROUP BY \"TYPE\", \"CATEGORY\"")
    List<AccountingRecord> summaryByMonth(@Param("familyId") Integer familyId,
                                          @Param("year") Integer year,
                                          @Param("month") Integer month);
}
