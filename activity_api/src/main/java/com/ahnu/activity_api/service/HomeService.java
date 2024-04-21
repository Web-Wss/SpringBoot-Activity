package com.ahnu.activity_api.service;

import com.ahnu.activity_api.model.Activity;
import com.ahnu.activity_api.model.Notice;

import java.util.List;

public interface HomeService {

/*
* 获取通知栏信息
    Notice getNotice();
* */
    Notice getNotice();

    /*
    * 获取活动列表
    * */
    List<Activity> getActivityList();


}
