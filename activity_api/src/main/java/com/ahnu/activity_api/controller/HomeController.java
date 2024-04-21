package com.ahnu.activity_api.controller;

import com.ahnu.activity_api.model.Activity;
import com.ahnu.activity_api.model.Notice;
import com.ahnu.activity_api.model.Result;
import com.ahnu.activity_api.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private HomeService homeService;

    /*
    * 获取通知栏信息
    * */
    @GetMapping("/getnotice")
    public Result getNotice() {
        Result result = new Result();
        Notice notice = homeService.getNotice();
        result.setSuccess(true);
        result.setData(notice);
        result.setMsg("通知栏信息");
        result.setCode(200);
        return result;
    }

//    获取活动列表
    @GetMapping("/activitylist")
    public Result getActivitylist() {
        Result result = new Result();
        List<Activity> activityList = homeService.getActivityList();
        result.setCode(200);
        result.setSuccess(true);
        result.setMsg("活动列表");
        result.setData(activityList);
//        activityList.forEach(i -> System.out.println(i));
        return result;
    }


}
