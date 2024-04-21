package com.ahnu.activity_api.service;


import com.ahnu.activity_api.model.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DataAdminService {

    //    获取用户数量
    Integer GetUserSum();
//    获取员工数量
Integer GetStaffSum();

//    获取活动数量
Integer GetActivitySum();
//    获取参与者数量
Integer GetParticipantSum();

    //    获取职工关注度
//    获取职工信息
    List<Staff> GetStaffInfo();
    Integer GetStaffSumInfo(@Param("staff_id")Integer staff_id);


    //    获取报名数量
//    获取活动名称
    List<Activity> GetActivityList();
    Integer GetEnrollSumList(@Param("activity_id")Integer activity_id);


    //    根据日期查询注册的用户数
    Integer GetUserSumInfo(@Param("user_time")String user_time);


//    获取通知列表内容
    List<Notice> GetNoticeList();

    //    修改通知内容
    Integer EditNoticeContent(Notice notice);

    //    获取意见反馈列表
    List<Option> GetOptionList();

    //    删除意见反馈根据id
    Integer DeleteOptionById(@Param("id")Integer id);

    //    获取失物招领列表
    List<LostArticle> GetLostArticleList();

    //    删除失物招领根据id
    Integer DeleteLostArticleById(@Param("id") Integer id);

}
