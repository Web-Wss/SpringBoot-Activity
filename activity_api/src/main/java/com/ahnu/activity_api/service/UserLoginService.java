package com.ahnu.activity_api.service;

import com.ahnu.activity_api.model.Staff;
import com.ahnu.activity_api.model.Users;
import org.apache.catalina.User;

public interface UserLoginService {
    Users userLogin(String phone, String password);

    Staff StaffLogin(Staff staff);

    //    用户注册
    Integer UserRegister(Users users);
}
