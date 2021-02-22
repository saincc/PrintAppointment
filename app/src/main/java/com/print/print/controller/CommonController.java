package com.print.print.controller;

import com.alibaba.fastjson.JSONObject;
import com.print.print.pojo.Problem;
import com.print.print.pojo.School;
import com.print.print.pojo.Shop;
import com.print.print.pojo.User;
import com.print.print.service.impl.ProblemServiceImpl;
import com.print.print.service.impl.SchoolServiceImpl;
import com.print.print.service.impl.ShopServiceImpl;
import com.print.print.service.impl.UserServiceImpl;
import com.sun.org.apache.xalan.internal.xsltc.compiler.Parser;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CommonController {
    @Autowired
    UserServiceImpl userService;
    @Autowired
    ShopServiceImpl shopService;
    @Autowired
    ProblemServiceImpl problemService;
    @Autowired
    SchoolServiceImpl schoolService;

    @PostMapping("/api/login")
    public Map login(@RequestBody HashMap<String, String> map){
        String username = map.get("username");
        String passwd = map.get("password");
        User user = userService.selectUserByName(username);
        HashMap<String, Object> response = new HashMap<>();
        HashMap<String, Object> responseData = new HashMap<>();
        if(passwd.equals(user.getPassword())) {
            if (user.getRole().equals("admin")) {
                responseData.put("token", user.getId());
                response.put("code", 20000);
                responseData.put("msg", "登录成功");
            } else if (user.getRole().equals("user")) {
                responseData.put("token", user.getId());
                response.put("code", 20000);
                responseData.put("msg", "登录成功");
            } else {
                responseData.put("token", 0);
                response.put("code", 20000);
                responseData.put("msg", "登录失败，用户权限不正确");
            }
        }else{
            responseData.put("token", 0);
            response.put("code", 20000);
            responseData.put("msg", "登录失败!用户名或密码错误");
        }
        response.put("data",responseData);
        return response;
    }

    @PostMapping("/api/logout")
    public Map logout(){
        HashMap<String, Object> response = new HashMap<>();
        HashMap<String, Object> responseData = new HashMap<>();
        response.put("code",20000);
        response.put("data","success");
        return response;
    }

    @GetMapping("/api/info")
    public Map info(@RequestParam("token") Integer id) {
        User user = userService.selectUserById(id);
        HashMap<String, Object> responseInfo = new HashMap<>();
        HashMap<String, Object> responseData = new HashMap<>();
        List<String> roles = new ArrayList<String>();
        roles.add(user.getRole());
        responseData.put("roles", roles);
        responseData.put("name",user.getUsername());
        responseData.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        responseInfo.put("code",20000);
        responseInfo.put("data",responseData);
        return responseInfo;
    }

    @PostMapping("/api/register")
    public Map register(@RequestBody Map<String,Object> map){
        HashMap<String, Object> response = new HashMap<>();
        response.put("code",20000);
        String username = map.get("username").toString();
        if(userService.selectUserByName(username) == null){
            String passwd = map.get("password").toString();
            String role = map.get("role").toString();
            User user = new User();
            user.setUsername(username);
            user.setPassword(passwd);
            user.setRole(role);
            if(userService.addUser(user)!=0){
                User newuser = userService.selectUserByName(username);
                if(role.equals("admin")){
                    Shop shop = new Shop();
                    shop.setUid(newuser.getId());
                    shop.setShopname(map.get("shopname").toString());
                    shop.setSid(Integer.parseInt(map.get("sid").toString()));
                    shop.setAddress(map.get("address").toString());
                    shop.setSupport(map.get("support").toString());
                    shop.setCredit(5.0);
                    shop.setSales(0);
                    shop.setAvatar("");
                    shopService.addShop(shop);
                }
                response.put("data","注册成功");
            }else{
                response.put("data","注册失败！");
            }

        }else{
            response.put("data","注册失败，用户名已存在");
        }
        return response;
    }

    @PostMapping("/api/problem")
    public Map submitProblem(@RequestBody HashMap<String, String> map){
        HashMap<String, Object> response = new HashMap<>();
        HashMap<String, Object> responseData = new HashMap<>();
        response.put("code",20000);
        Integer uid = Integer.parseInt(map.get("uid"));
        Problem problem = new Problem();
        problem.setProblem(map.get("problem"));
        problem.setUid(uid);
        problemService.addProblem(problem);
        response.put("data","问题反馈成功！");
        return response;
    }

    @GetMapping("/api/school")
    public Map getAllSchool() {
        HashMap<String, Object> responseInfo = new HashMap<>();
        HashMap<String, Object> responseData = new HashMap<>();
        List<School> schools =  schoolService.selectAllSchool();
        responseData.put("items", schools);
        responseInfo.put("code",20000);
        responseInfo.put("data",responseData);
        return responseInfo;
    }
}
