package com.ahnu.activity_api.dao;

import com.ahnu.activity_api.model.Activity;
import com.ahnu.activity_api.model.Notice;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HomeDao {
//    获取通知栏信息
    Notice getNotice();

//    获取活动列表
    List<Activity> getActivityList();
}
