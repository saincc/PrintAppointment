package com.print.print.service;

import com.print.print.pojo.User;

public interface UserService {
    User selectUserByName(String username);

    User selectUserById(Integer id);

    Integer addUser(User user);
}
