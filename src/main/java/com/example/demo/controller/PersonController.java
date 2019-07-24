package com.example.demo.controller;

import com.example.demo.domain.PersonDO;
import com.example.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by FlySheep on 17/3/25.
 */
//@EnableTransactionManagement  // 需要事务的时候加上
@RestController
public class PersonController {
    @Autowired
    private UserMapper personMapper;

    @Autowired
    private DataSource dataSource;



    @RequestMapping("/selectAll")
    public List<PersonDO> selectAll() {
        dataSource.toString();
        return personMapper.listAll();
    }
}
