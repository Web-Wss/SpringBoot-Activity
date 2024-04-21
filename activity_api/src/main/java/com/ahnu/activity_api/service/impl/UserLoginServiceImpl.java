package com.ahnu.activity_api.service.impl;

import com.ahnu.activity_api.dao.UserLoginDao;
import com.ahnu.activity_api.model.Staff;
import com.ahnu.activity_api.model.Users;
import com.ahnu.activity_api.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserLoginServiceImpl implements UserLoginService {
    @Autowired
    private UserLoginDao userLoginDao;

    @Override
    public Users userLogin(String phone, String password) {
        return userLoginDao.userLogin(phone, password);
    }

    @Override
    public Staff StaffLogin(Staff staff) {
        return userLoginDao.StaffLogin(staff);
    }

    @Override
    public Integer UserRegister(Users users) {
        return userLoginDao.UserRegister(users);
    }
}
