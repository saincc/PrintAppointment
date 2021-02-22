package com.print.print.controller;

import com.print.print.pojo.Order;
import com.print.print.pojo.School;
import com.print.print.pojo.Shop;
import com.print.print.service.impl.OrderServiceImpl;
import com.print.print.service.impl.SchoolServiceImpl;
import com.print.print.service.impl.ShopServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class UserController {
    @Autowired
    OrderServiceImpl orderService;

    @Autowired
    ShopServiceImpl shopService;
    @Autowired
    SchoolServiceImpl schoolService;

    @PostMapping("/api/user/order")
    public Map getOrderByUid(@RequestBody HashMap<String, String> map){
        HashMap<String, Object> response = new HashMap<>();
        HashMap<String, Object> responseData = new HashMap<>();
        response.put("code",20000);
        Integer uid = Integer.parseInt(map.get("uid"));
        List<Order> orders = orderService.selectOrderByUid(uid);
        responseData.put("items",orders);
        response.put("data",responseData);
        return response;
    }

    @GetMapping("/api/user/allshop")
    public Map getAllShop(){
        HashMap<String, Object> response = new HashMap<>();
        HashMap<String, Object> responseData = new HashMap<>();
        response.put("code",20000);
        List<Shop> shops = shopService.selectAllShop();
        responseData.put("items",shops);
        response.put("data",responseData);
        return response;
    }

    @PostMapping("/api/user/shop")
    public Map getShopBySid(@RequestBody HashMap<String, String> map){
        HashMap<String, Object> response = new HashMap<>();
        HashMap<String, Object> responseData = new HashMap<>();
        response.put("code",20000);
        List<Shop> shops = shopService.selectShopBySid(Integer.parseInt(map.get("sid")));
        responseData.put("items",shops);
        response.put("data",responseData);
        return response;
    }

    @PostMapping("/api/user/shopid")
    public Map getShopByPid(@RequestBody HashMap<String, String> map){
        HashMap<String, Object> response = new HashMap<>();
        HashMap<String, Object> responseData = new HashMap<>();
        response.put("code",20000);
        Shop shops = shopService.selectShopById(Integer.parseInt(map.get("pid")));
        response.put("data",shops.getSid());
        return response;
    }

    @PostMapping("/api/user/uploadfile")
    public Map uploadFile(@RequestParam("file") MultipartFile file){
        HashMap<String, Object> response = new HashMap<>();
        response.put("code",20000);
        String fileName = file.getOriginalFilename();
        String path = System.getProperty("user.dir") + "\\src\\main\\resources\\files\\" +fileName;

        File dest = new File(path);
        try {
            //上传文件
            file.transferTo(dest); //保存文件
            System.out.print("保存文件路径:"+path+"\n");
            response.put("data",path);
        } catch (IOException e) {
            response.put("data","上传失败");
        }
        return response;
    }

    @PostMapping("/api/user/ordersubmit")
    public Map orderSubmit(@RequestBody HashMap<String, String> map) throws ParseException {
        HashMap<String, Object> response = new HashMap<>();
        HashMap<String, Object> responseData = new HashMap<>();
        response.put("code",20000);
        Order order = new Order();
        order.setUid(Integer.parseInt(map.get("uid")));
        order.setSid(Integer.parseInt(map.get("sid")));
        order.setPid(Integer.parseInt(map.get("pid")));

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        order.setDate(sdf.parse(map.get("date")));
        order.setFile(map.get("file"));
        order.setNeeds(map.get("needs"));
        order.setOther_needs(map.get("otherneeds"));
        order.setState("unprint");

        if(orderService.addOrder(order) != 0){
            response.put("data","提交成功！");
        }else{
            response.put("data","提交失败！");
        }
        return response;
    }

    @PostMapping("/api/user/filtershop")
    public Map filterShop(@RequestBody HashMap<String, Object> map){
        HashMap<String, Object> response = new HashMap<>();
        HashMap<String, Object> responseData = new HashMap<>();
        response.put("code",20000);
        List<Shop> shops = shopService.selectAllShop();
        List<Shop> result = new ArrayList<>();
        for(Shop shop : shops){
            if(map.get("shopname").toString() != ""){
                if(!shop.getShopname().contains(map.get("shopname").toString())){
                    continue;
                }
            }
            if(map.get("sid").toString() != ""){
                if(shop.getSid() != Integer.parseInt(map.get("sid").toString())){
                    continue;
                }
            }
            if(map.get("support").toString() != "[]"){
                String[] support = map.get("support").toString().substring(1,map.get("support").toString().length()-1).split(", ");
                List<String> shopsu = Arrays.asList(shop.getSupport().substring(1,shop.getSupport().length()-1).split(", "));
                Boolean flag = Boolean.TRUE;
                for(String su : support){
                    if(!shopsu.contains(su)){
                        flag = Boolean.FALSE;
                        break;
                    }
                }
                if(!flag){
                    continue;
                }
            }
            String[] credit = map.get("credit").toString().substring(1,map.get("credit").toString().length()-1).split(", ");
            String[] sales = map.get("sales").toString().substring(1,map.get("sales").toString().length()-1).split(", ");
            if(shop.getCredit()<Double.parseDouble(credit[1]) || shop.getCredit()>Double.parseDouble(credit[1])){
                continue;
            }
            if(shop.getSales()<Integer.parseInt(sales[0]) || shop.getSales()>Integer.parseInt(sales[1])){
                continue;
            }
            result.add(shop);
        }
        responseData.put("items",result);
        response.put("data",responseData);
        return response;
    }

    @PostMapping("/api/user/filterorder")
    public Map filterOrder(@RequestBody HashMap<String, Object> map) throws ParseException {
        HashMap<String, Object> response = new HashMap<>();
        HashMap<String, Object> responseData = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        response.put("code",20000);
        List<Order> orders = orderService.selectOrderByUid(Integer.parseInt(map.get("uid").toString()));
        List<Order> result = new ArrayList<>();
        for(Order order : orders){
            if(!map.get("file").toString().equals("")){
                if(!order.getFile().contains(map.get("file").toString())){
                    continue;
                }
            }
            if(!map.get("state").toString().equals("")){
                if(!order.getState().equals(map.get("state").toString())){
                    continue;
                }
            }
            if(!map.get("date").toString().equals("")){
                String[] dates = map.get("date").toString().substring(1,map.get("date").toString().length()-1).split(", ");
                if(order.getDate().getTime()<sdf.parse(dates[0]).getTime() || order.getDate().getTime()>sdf.parse(dates[1]).getTime()){
                    continue;
                }
            }
            result.add(order);
        }
        responseData.put("items",result);
        response.put("data",responseData);
        return response;
    }

    @PostMapping("/api/user/submitrate")
    public Map submitRate(@RequestBody HashMap<String, String> map) throws ParseException {
        HashMap<String, Object> response = new HashMap<>();
        HashMap<String, Object> responseData = new HashMap<>();
        response.put("code",20000);
        Shop shop = shopService.selectShopById(Integer.parseInt(map.get("pid")));
        Double value = Double.parseDouble(map.get("value"));
        Double credit = (shop.getCredit()*shop.getSales() + value)/ (shop.getSales()+1);
        shop.setCredit(credit);
        shop.setSales(shop.getSales()+1);
        Order order = orderService.selectOrderById(Integer.parseInt(map.get("id")));
        order.setState("done");

        if(shopService.updateShop(shop) != 0 && orderService.updateOrder(order) != 0){
            response.put("data","提交成功！");
        }else{
            response.put("data","提交失败！");
        }
        return response;
    }

    @PostMapping("/api/user/orderchange")
    public Map orderChange(@RequestBody HashMap<String, String> map) throws ParseException {
        HashMap<String, Object> response = new HashMap<>();
        HashMap<String, Object> responseData = new HashMap<>();
        response.put("code",20000);
        Order order = orderService.selectOrderById(Integer.parseInt(map.get("id")));
        order.setSid(Integer.parseInt(map.get("sid")));
        order.setPid(Integer.parseInt(map.get("pid")));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        order.setDate(sdf.parse(map.get("date")));
        order.setFile(map.get("file"));
        order.setNeeds(map.get("needs"));
        order.setOther_needs(map.get("otherneeds"));

        if(orderService.updateOrder(order) != 0){
            response.put("data","修改成功！");
        }else{
            response.put("data","修改失败！");
        }
        return response;
    }

}

