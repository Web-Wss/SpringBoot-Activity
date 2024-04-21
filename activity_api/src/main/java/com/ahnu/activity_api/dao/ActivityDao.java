package com.ahnu.activity_api.dao;


import com.ahnu.activity_api.model.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ActivityDao {

//    获取活动列表
    List<Activity> getActivityList();

//    获取活动分类
    List<Classification> getClassificationList();

//    获取日期列表
    List<Time> getTimeList();

//    通过活动id获取活动内容
    Activity getActivityById(@Param("activity_id") Integer activity_id);
//    添加浏览次数
    Integer updateBrowseSum(@Param("activity_id") Integer activity_id);


//    通过活动id、用户id、获取活动内容、日期、参与者、参与者职工等
//        获取活动内容、日期
    Activity getActivityAndDate(@Param("activity_id") Integer activity_id);
//    通过时间id获取时间内容
    Time getTimeListByTimeId(@Param("time_id") Integer time_id,@Param("activity_id") Integer activity_id);

    //        获取参与者
    Participant getParticipantList(@Param("participant_id") Integer participant_id);

//        获取参与职工
    Users getUserInfoByUserId(@Param("user_id") Integer user_id);
    Staff getStaffInfo(@Param("staff_id") Integer staff_id);


//    提交报名信息
    //            提交之前查询表中是否已经有该参与者数据
    Integer selectParticipantAndTime(@Param("timeId") Integer timeId,@Param("participantId") Integer participantId,@Param("activity_id") Integer activity_id);
    Integer pushEnroll(Enroll enroll);

//    收藏查询
    CollectionTab selectCollection(@Param("userId") Integer userId, @Param("activityId") Integer activityId);

//    收藏或取消收藏
//    添加收藏
    Integer insertCollection(CollectionTab collectionTab);
//    删除收藏
    Integer deleteCollection(@Param("userId") Integer userId, @Param("activityId") Integer activityId);


//      开启签到
    List<Enroll> getEnrollList(@Param("timeId") Integer timeId);
    Integer updatetimetabissignin(Time time);
    Integer insertSignIn(SignIn signIn);

//    关闭签到
//    根据报名id删除签到表的内容
    Integer DeleteSignIn(Integer enroll_id);


//    根据报名id查询用户是否已经签到
    SignIn getSignInByEnrollId(@Param("enroll_id") Integer enroll_id);

//    用户签到请求
    Integer updateSignIn(@Param("sign_in_id") Integer sign_in_id);

//    根据分类id获取活动列表
    List<Activity> SearchListByClassification(@Param("classification_id") Integer classification_id);

    //    模糊搜索活动名称
    List<Activity>SearchActivityNameLike(@Param("name")String name);


}
