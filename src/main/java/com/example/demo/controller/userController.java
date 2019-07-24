package com.example.demo.controller;

import com.example.demo.domain.PersonDO;
import com.example.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class userController {
    @Autowired
    private UserMapper userMapper;

//    @RequestMapping("/")
//    public List<PersonDO> getAll(){
//        List<PersonDO> s = userMapper.listAll();
//        return s;
//    }
}
