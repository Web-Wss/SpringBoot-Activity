package com.ahnu.activity_api.dao;

import com.ahnu.activity_api.model.SignIn;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SignInAdminDao {

//    获取签到列表
    List<SignIn> GetSignInList();


//    获取签到列表根据时间id和活动id
    List<SignIn> GetSignInListByTimeIdAndActivityId(@Param("activity_id")Integer activity_id,
                                                    @Param("time_id")Integer time_id);


}
