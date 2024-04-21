package com.ahnu.activity_api.controller;


import com.ahnu.activity_api.dao.SignInAdminDao;
import com.ahnu.activity_api.model.Result;
import com.ahnu.activity_api.model.SignIn;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/signinadmin")
public class SignInAdminController {

    @Autowired
    private SignInAdminDao signInAdminDao;

//    获取签到列表
    @GetMapping("/getsigninlist")
    public Result GetSignInList(){
        Result result = new Result();
        List<SignIn> signIns = signInAdminDao.GetSignInList();
        result.setSuccess(true);
        result.setCode(200);
        result.setMsg("获取签到列表");
        result.setData(signIns);
        return result;
    }


//    获取签到列表根据活动id和时间id
    @PostMapping("/getsigninlistbytimeidandactivityid")
    public Result GetSignInListByTimeIdAndActivityId(@Param("activity_id")Integer activity_id,
                                                     @Param("time_id")Integer time_id){
        Result result = new Result();
        List<SignIn> signIns = signInAdminDao.GetSignInListByTimeIdAndActivityId(activity_id, time_id);
        result.setSuccess(true);
        result.setCode(200);
        result.setMsg("获取签到列表根据活动id和时间id");
        result.setData(signIns);
        return result;
    }




}
