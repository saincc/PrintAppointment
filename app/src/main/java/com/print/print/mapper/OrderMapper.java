package com.print.print.mapper;

import com.print.print.pojo.Order;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OrderMapper {

    Order selectOrderById(Integer id);

    List<Order> selectOrderByUid(Integer uid);

    List<Order> selectOrderByPidState(Integer pid,String state);

    Integer addOrder(Order order);

    Integer updateOrder(Order order);

}
