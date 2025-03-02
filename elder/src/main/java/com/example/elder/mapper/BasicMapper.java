package com.example.elder.mapper;

import com.example.elder.pojo.Basic;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BasicMapper {
    @Insert("insert into elder.a1_basic (id, date, reason, name) VALUES (#{id},#{date},#{reason},#{name})")
    void add_1(Basic basic);

    @Insert("insert into elder.a2_person(name, sex, born, idcard, socialcard, nation, education, religion, marriage, resident, medical, income, stupid, mentaldis, chronic, fall, lost, choke, suicide, elseill) VALUES (#{name},#{sex},#{born},#{idcard},#{socialcard},#{nation},#{education},#{religion},#{marriage},#{resident},#{medical},#{income},#{stupid},#{mentaldis},#{chronic},#{fall},#{lost},#{choke},#{suicide},#{elseill})")
    void add_2(Basic basic);

    @Insert("insert into elder.provider(eldername, providername, relation, contactname, contactphone) VALUES (#{eldername},#{providername},#{relation},#{contactname},#{contactphone})")
    void add_pr(Basic basic);
}
