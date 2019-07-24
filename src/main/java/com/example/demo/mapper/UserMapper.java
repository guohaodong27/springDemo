package com.example.demo.mapper;

import com.example.demo.domain.PersonDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("select * from person")
    List<PersonDO> listAll();
}
