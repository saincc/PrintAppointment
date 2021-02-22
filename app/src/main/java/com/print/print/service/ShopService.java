package com.print.print.service;

import com.print.print.pojo.Shop;

import java.util.List;

public interface ShopService {
    List<Shop> selectAllShop();

    List<Shop> selectShopBySid(Integer sid);

    Shop selectShopById(Integer id);

    Shop selectShopByUid(Integer uid);

    Integer addShop(Shop shop);

    Integer updateShop(Shop shop);

    List<Shop> filterShop(String shopname,Integer sid,String support,Double scredit,Double ecredit,Integer ssales,Integer esales);

}
