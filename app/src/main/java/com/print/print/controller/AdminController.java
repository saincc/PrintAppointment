package com.print.print.controller;

import com.print.print.pojo.Order;
import com.print.print.pojo.School;
import com.print.print.pojo.Shop;
import com.print.print.service.impl.OrderServiceImpl;
import com.print.print.service.impl.SchoolServiceImpl;
import com.print.print.service.impl.ShopServiceImpl;
import com.print.print.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class AdminController {
    @Autowired
    OrderServiceImpl orderService;

    @Autowired
    ShopServiceImpl shopService;
    @Autowired
    SchoolServiceImpl schoolService;

    @PostMapping("/api/admin/order")
    public Map getOrder(@RequestBody HashMap<String, String> map){
        HashMap<String, Object> response = new HashMap<>();
        HashMap<String, Object> responseData = new HashMap<>();
        response.put("code",20000);
        Integer uid = Integer.parseInt(map.get("uid"));
        Shop shop = shopService.selectShopByUid(uid);
        Integer pid = shop.getId();
        String state = map.get("state");
        List<Order> orders = orderService.selectOrderByPidState(pid,state);
        responseData.put("items",orders);
        response.put("data",responseData);
        return response;
    }

    @PostMapping("/api/admin/info")
    public Map getInfo(@RequestBody HashMap<String, Object> map){
        HashMap<String, Object> response = new HashMap<>();
        HashMap<String, Object> responseData = new HashMap<>();
        response.put("code",20000);
        Integer uid = Integer.parseInt(map.get("uid").toString());
        Shop shop = shopService.selectShopByUid(uid);

        responseData.put("items",shop);
        response.put("data",responseData);
        return response;
    }

    @PostMapping("/api/admin/school")
    public Map getSchool(@RequestBody HashMap<String, Object> map){
        HashMap<String, Object> response = new HashMap<>();
        HashMap<String, Object> responseData = new HashMap<>();
        response.put("code",20000);
        Integer sid = Integer.parseInt(map.get("sid").toString());
        School school = schoolService.selectSchoolById(sid);
        responseData.put("items",school);
        response.put("data",responseData);
        return response;
    }

    @PostMapping("/api/admin/updateshop")
    public Map updateShop(@RequestBody HashMap<String, Object> map){
        HashMap<String, Object> response = new HashMap<>();
        response.put("code",20000);
        Integer uid = Integer.parseInt(map.get("uid").toString());
        Shop shop = shopService.selectShopByUid(uid);
        shop.setShopname(map.get("shopname").toString());
        shop.setAvatar(map.get("avatar").toString());
        shop.setAddress(map.get("address").toString());
        shop.setSupport(map.get("support").toString());
        shop.setSid(Integer.parseInt(map.get("sid").toString()));

        if(shopService.updateShop(shop) != 0){
            response.put("data","修改成功！");
        }else{
            response.put("data","修改失败！");
        }
        return response;
    }

    @PostMapping("/api/admin/updatestate")
    public Map updateState(@RequestBody HashMap<String, Object> map){
        HashMap<String, Object> response = new HashMap<>();
        response.put("code",20000);
        Integer id = Integer.parseInt(map.get("id").toString());
        Order order = orderService.selectOrderById(id);
        order.setState("printed");
        if(orderService.updateOrder(order) != 0){
            response.put("data","修改成功！");
        }else{
            response.put("data","修改失败！");
        }
        return response;
    }
}
