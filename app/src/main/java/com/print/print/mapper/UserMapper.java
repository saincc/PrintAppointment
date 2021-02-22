package com.print.print.mapper;

import com.print.print.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
    /**
     * 根据用户名查询用户
     *
     * @param username
     * @return
     */
    User selectUserByName(String username);

    User selectUserById(Integer id);

    Integer addUser(User user);
}
