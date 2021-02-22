package com.print.print.mapper;

import com.print.print.pojo.Shop;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ShopMapper {

    List<Shop> selectAllShop();

    List<Shop> selectShopBySid(Integer sid);

    Shop selectShopById(Integer id);

    Shop selectShopByUid(Integer uid);

    Integer addShop(Shop shop);

    Integer updateShop(Shop shop);

    List<Shop> filterShop(String shopname,Integer sid,String support,Double scredit,Double ecredit,Integer ssales,Integer esales);

}
