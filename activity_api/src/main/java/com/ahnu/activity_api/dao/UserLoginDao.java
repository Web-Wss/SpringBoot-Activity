package com.ahnu.activity_api.dao;


import com.ahnu.activity_api.model.Staff;
import com.ahnu.activity_api.model.Users;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
public interface UserLoginDao {
    Users userLogin(@Param("phone") String phone,@Param("password") String password);

    Staff StaffLogin(Staff staff);

//    用户注册
    Integer UserRegister(Users users);
}
