package com.print.print.service.impl;

import com.print.print.mapper.ShopMapper;
import com.print.print.pojo.Shop;
import com.print.print.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {
    @Autowired
    private ShopMapper shopMapper;

    @Override
    public List<Shop> selectShopBySid(Integer sid){
        return shopMapper.selectShopBySid(sid);
    }

    @Override
    public Integer addShop(Shop shop){
        return shopMapper.addShop(shop);
    }

    @Override
    public Shop selectShopById(Integer id){
        return shopMapper.selectShopById(id);
    }
    @Override
    public Shop selectShopByUid(Integer uid){
        return shopMapper.selectShopByUid(uid);
    }

    @Override
    public Integer updateShop(Shop shop){
        return shopMapper.updateShop(shop);
    }

    @Override
    public List<Shop> selectAllShop(){
        return shopMapper.selectAllShop();
    }

    @Override
    public List<Shop> filterShop(String shopname,Integer sid,String support,Double scredit,Double ecredit,Integer ssales,Integer esales){
        return shopMapper.filterShop(shopname,sid,support,scredit,ecredit,ssales,esales);
    }

}
