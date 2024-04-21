package com.ahnu.activity_api.dao;


import com.ahnu.activity_api.model.Activity;
import com.ahnu.activity_api.model.Enroll;
import com.ahnu.activity_api.model.Time;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EnrollAdminDao {

//    获取报名列表
    List<Enroll> GetEnrollList();


//    获取活动名称列表
    List<Activity> GetActivityNameList();

//    获取活动对应的时间
    List<Time> GetTimeListByActivityId(@Param("activity_id")Integer activity_id);

//    获取报名列表根据活动id和时间id
    List<Enroll> GetEnrollListByTimeAndActivityId(@Param("activity_id")Integer activity_id,@Param("time_id")Integer time_id);


//    参与者查询
    List<Enroll> GetParticipantName(@Param("participant_name")String participant_name);


//    删除报名信息根据ID
    Integer DeleteEnrollById(@Param("id")Integer id);


}
