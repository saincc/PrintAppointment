package com.print.print.service;

import com.print.print.pojo.Order;

import java.util.List;

public interface OrderService {
    Order selectOrderById(Integer id);

    List<Order> selectOrderByUid(Integer uid);

    List<Order> selectOrderByPidState(Integer pid,String state);

    Integer addOrder(Order order);

    Integer updateOrder(Order order);
}
