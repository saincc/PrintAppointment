package com.print.print.service.impl;

import com.print.print.mapper.UserMapper;
import com.print.print.pojo.User;
import com.print.print.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User selectUserByName(String username){
        return userMapper.selectUserByName(username);
    }

    @Override
    public User selectUserById(Integer id){
        return userMapper.selectUserById(id);
    }

    @Override
    public Integer addUser(User user){
        return userMapper.addUser(user);
    }
}
