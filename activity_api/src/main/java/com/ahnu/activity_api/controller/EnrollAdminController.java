package com.ahnu.activity_api.controller;


import com.ahnu.activity_api.dao.EnrollAdminDao;
import com.ahnu.activity_api.model.Activity;
import com.ahnu.activity_api.model.Enroll;
import com.ahnu.activity_api.model.Result;
import com.ahnu.activity_api.model.Time;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/enrolladmin")
public class EnrollAdminController {

    @Autowired
    private EnrollAdminDao enrollAdminDao;

//    获取报名列表
    @GetMapping("/getenrolllist")
    public Result GetEnrollList(){
        Result result = new Result();
        List<Enroll> enrolls = enrollAdminDao.GetEnrollList();
        result.setSuccess(true);
        result.setCode(200);
        result.setMsg("获取报名列表");
        result.setData(enrolls);
        return result;
    }

//    获取活动名称列表
    @GetMapping("/getactivitynamelist")
    public Result GetActivityNameList(){
        Result result = new Result();
        List<Activity> activities = enrollAdminDao.GetActivityNameList();
        result.setSuccess(true);
        result.setCode(200);
        result.setMsg("获取活动名称列表");
        result.setData(activities);
        return result;
    }

//    获取活动对应的时间列表
    @PostMapping("/gettimelistbyactivityid")
    public Result GetTimeListByActivityId(@Param("activity_id")Integer activity_id){
        Result result = new Result();
        List<Time> timeList = enrollAdminDao.GetTimeListByActivityId(activity_id);
        result.setSuccess(true);
        result.setCode(200);
        result.setMsg("获取活动对应时间");
        result.setData(timeList);
        return result;
    }

//    获取报名列表根据活动id和时间id
    @PostMapping("/getenrolllistbytimeandactivityid")
    public Result GetEnrollListByTimeAndActivityId(@Param("activity_id")Integer activity_id,
                                                   @Param("time_id")Integer time_id){
        Result result = new Result();
        List<Enroll> enrolls = enrollAdminDao.GetEnrollListByTimeAndActivityId(activity_id, time_id);
        result.setSuccess(true);
        result.setCode(200);
        result.setMsg("获取报名列表根据活动id和时间id");
        result.setData(enrolls);
        return result;
    }


//    参与者查询
    @PostMapping("/participantname")
    public Result GetParticipantName(@Param("participant_name")String participant_name){
        Result result = new Result();
        List<Enroll> enrolls = enrollAdminDao.GetParticipantName(participant_name);
        result.setSuccess(true);
        result.setCode(200);
        result.setMsg("参与者查询");
        result.setData(enrolls);
        return result;
    }


//    删除报名信息根据ID
    @PostMapping("/deleteenrollbyid")
    public Result DeleteEnrollById(@Param("id")Integer id){
        Result result = new Result();
        Integer integer = enrollAdminDao.DeleteEnrollById(id);
        result.setCode(200);
        result.setSuccess(true);
        result.setMsg("删除报名成功");
        result.setData(integer);
        return result;
    }


}
