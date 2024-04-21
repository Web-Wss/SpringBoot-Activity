package com.ahnu.activity_api.controller;

import com.ahnu.activity_api.model.Result;
import com.ahnu.activity_api.model.Staff;
import com.ahnu.activity_api.model.Users;
import com.ahnu.activity_api.service.UserLoginService;
import com.ahnu.activity_api.utils.JWTUtils;
import org.apache.catalina.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserLoginService userLoginService;
//  用户登录
    @PostMapping("/userlogin")
    public Result userLogin(String phone,String password) {
        Result result = new Result();
        Users users = userLoginService.userLogin(phone,password);
        Map<String,Object> map = new HashMap<>();
        map.put("users",users);
        if (users != null) {
            String token = JWTUtils.generateToken(phone);
            map.put("token",token);
            map.put("role","user");
            result.setCode(200);
            result.setSuccess(true);
            result.setData(map);
            result.setMsg("登录成功");
        }else {
            result.setCode(200);
            result.setSuccess(true);
            result.setData(null);
            result.setMsg("登录失败");
        }
        return result;
    }

//    职工登录
    @PostMapping("/stafflogin")
    public Result StaffLogin(String phone,String password) {
        Result result = new Result();
        Staff staff = new Staff();
        staff.setStaffPhone(phone);
        staff.setPassword(password);
        Staff staff1 = userLoginService.StaffLogin(staff);
        Map<String,Object> map = new HashMap<>();
        map.put("staff",staff1);
        if (staff1 != null) {
            String token = JWTUtils.generateToken(phone);
            map.put("token",token);
            map.put("role","staff");
            result.setCode(200);
            result.setSuccess(true);
            result.setMsg("登录成功");
            result.setData(map);
        }else {
            result.setCode(200);
            result.setSuccess(true);
            result.setMsg("登录失败");
            result.setData(null);
        }
        return result;
    }

//    用户注册
    @PostMapping("/userregister")
    public Result UserRegister(@Param("username")String username,
                               @Param("password")String password,
                               @Param("parentName")String parentName,
                               @Param("phone")String phone) {
        Result result = new Result();
        Users users = new Users();
        users.setUsername(username);
        users.setPassword(password);
        users.setParentName(parentName);
        users.setPhone(phone);
        Integer integer = userLoginService.UserRegister(users);
        result.setSuccess(true);
        result.setCode(200);
        result.setMsg("用户注册成功");
        result.setData(integer);
        return result;
    }



}
