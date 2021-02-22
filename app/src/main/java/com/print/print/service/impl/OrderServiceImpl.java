package com.print.print.service.impl;

import com.print.print.mapper.OrderMapper;
import com.print.print.pojo.Order;
import com.print.print.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public Order selectOrderById(Integer id){
        return orderMapper.selectOrderById(id);
    }

    @Override
    public List<Order> selectOrderByUid(Integer uid){
        return orderMapper.selectOrderByUid(uid);
    }

    @Override
    public List<Order> selectOrderByPidState(Integer pid,String state){
        return orderMapper.selectOrderByPidState(pid,state);
    }

    @Override
    public Integer addOrder(Order order){
        return orderMapper.addOrder(order);
    }

    @Override
    public Integer updateOrder(Order order){
        return orderMapper.updateOrder(order);
    }
}
