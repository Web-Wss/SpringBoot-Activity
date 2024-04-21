package com.ahnu.activity_api.controller;


import com.ahnu.activity_api.dao.ActivityDao;
import com.ahnu.activity_api.model.*;
import com.ahnu.activity_api.model.CollectionTab;
import javafx.geometry.Pos;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/activity")
public class ActivityController {

    @Autowired
    private ActivityDao activityDao;

//    获取活动列表
    @GetMapping("/getactivitylist")
    public Result getActivityList() {
        Result result = new Result();
        List<Activity> activityList = activityDao.getActivityList();
        result.setSuccess(true);
        result.setMsg("获取活动列表");
        result.setCode(200);
        result.setData(activityList);
        return result;
    }

//    获取活动分类
    @GetMapping("/getclassificationlist")
    public Result getClassificationList() {
        Result result = new Result();
        List<Classification> classificationList = activityDao.getClassificationList();
        result.setSuccess(true);
        result.setCode(200);
        result.setMsg("活动分类列表");
        result.setData(classificationList);
        return result;
    }

//    获取日期列表
    @GetMapping("/gettimelist")
    public Result getTimeList() {
        Result result = new Result();
        List<Time> timeList = activityDao.getTimeList();
        result.setCode(200);
        result.setMsg("日期列表");
        result.setSuccess(true);
        result.setData(timeList);
        return result;
    }

//    通过活动id获取活动内容
    @PostMapping("/getactivitybyid")
    public Result getActivityById(@Param("activity_id") Integer activity_id) {
        Result result = new Result();
//       添加浏览次数
        Integer integer = activityDao.updateBrowseSum(activity_id);
        Activity activityById = activityDao.getActivityById(activity_id);
        result.setSuccess(true);
        result.setMsg("获取活动具体内容");
        result.setCode(200);
        result.setData(activityById);
        return result;
    }

//    通过活动id、用户id、获取活动内容、日期、参与者、参与者职工等
    @PostMapping("/getactivityanddate")
    public Result getActivityAndDate(Integer activity_id, Integer user_id) {
        Result result = new Result();
        Map<String,Object> map = new HashMap<>();
        List<Staff> staffList = new ArrayList<>();
        List timeList = new ArrayList<>();
        List<Participant> participantList = new ArrayList<>();
//        获取活动内容、日期
        Activity activityAndDate = activityDao.getActivityAndDate(activity_id);
//        获取活动日期，查时间表通过timeId
        String timeId = activityAndDate.getTimeId();
        String[] strArray2 = timeId.substring(1, timeId.length() - 1).split(",");
        int[] intArray2 = new int[strArray2.length];
        for (int i = 0; i < strArray2.length; i ++) {
            intArray2[i] = Integer.parseInt(strArray2[i]);
        }
        for (int i = 0; i < intArray2.length; i ++) {
//            通过时间id查询时间内容
            Integer time_id = intArray2[i];
            Time timeListByTimeId = activityDao.getTimeListByTimeId(time_id,activity_id);
            timeList.add(timeListByTimeId);
        }
        map.put("timeList",timeList);
        map.put("activityAndDate",activityAndDate);
//        参与者职工id数组
        String activityStaffId = activityAndDate.getActivityStaffId();
        String[] strArray = activityStaffId.substring(1, activityStaffId.length() - 1).split(",");
        int[] intArray = new int[strArray.length];
        for (int i = 0; i < strArray.length; i++) {
            intArray[i] = Integer.parseInt(strArray[i]);
        }
        for (int i = 0; i < intArray.length; i ++) {
//            通过职工id查询职工信息
            Integer staff_id = intArray[i];
            Staff staffInfo = activityDao.getStaffInfo(staff_id);
            staffList.add(staffInfo);
        }
        map.put("staff",staffList);
//        根据用户id获取用户信息
        Users userInfoByUserId = activityDao.getUserInfoByUserId(user_id);
        String participantId = userInfoByUserId.getParticipantId();
        String[] strArray1 = participantId.substring(1, participantId.length() - 1).split(",");
        int[] intArray1 = new int[strArray1.length];
        for (int i = 0; i < strArray1.length; i++) {
            intArray1[i] = Integer.parseInt(strArray1[i]);
        }
        for (int i = 0; i < intArray1.length; i ++) {
//            获取参与者
            Integer participant_id = intArray1[i];
            System.out.println("participant_id:"+participant_id);
            Participant participantInfo = activityDao.getParticipantList(participant_id);
            participantList.add(participantInfo);
        }
        map.put("participant",participantList);

//        获取参与职工
        result.setMsg("获取活动内容、日期、参与者、参与者职工等");
        result.setSuccess(true);
        result.setCode(200);
        result.setData(map);
        return result;
    }

//    提交报名信息
    @PostMapping("/enroll")
    public Result Enroll(@Param("userId") Integer userId,@Param("contractId") Integer contractId ,@Param("userRemarks") String userRemarks ,@Param("timeId") Integer timeId,@Param("staffId") Integer staffId,
                         @Param("participantId") Integer[] participantId,@Param("activityName") String activityName,@Param("activityId") Integer activityId) {
        Result result = new Result();
        Enroll enroll = new Enroll();

        for (int i = 0; i < participantId.length; i ++) {
            enroll.setParticipantId(participantId[i]);
            enroll.setEnrollId(null);
            enroll.setUserId(userId);
            enroll.setActivityId(activityId);
            enroll.setTimeId(timeId);
            enroll.setStaffId(staffId);
            if (contractId == 0) {
                enroll.setContractId(null);//合同id
            }else {
                enroll.setContractId(contractId);//合同id
            }
            enroll.setUserRemarks(userRemarks);
            enroll.setPublishTime(new Date());
            System.out.println(enroll);
//            提交报名信息
//            提交之前查询表中是否已经有该参与者数据
            Integer integer = activityDao.selectParticipantAndTime(timeId, participantId[i],activityId);
            System.out.println("integer == " + integer);
            if (integer != 1) {
//                执行插入
                Integer integer1 = activityDao.pushEnroll(enroll);
//                System.out.println(integer1);
                if (integer1 == 1) {
                    result.setCode(200);
                    result.setSuccess(true);
                    result.setMsg("报名反馈信息");
                    result.setData("报名成功");
                }else {
                    result.setCode(404);
                    result.setSuccess(false);
                    result.setMsg("报名反馈信息");
                    result.setData("报名失败");
                }

            }else {
                result.setCode(200);
                result.setSuccess(true);
                result.setMsg("报名反馈信息");
                result.setData("参与者已经包过名了哟");
            }
        }

        return result;
    }

//    收藏查询
    @PostMapping("/collection")
    public Result Collection(@Param("userId") Integer userId,@Param("activityId") Integer activityId) {
        Result result = new Result();
        System.out.println(userId);
        System.out.println(activityId);
        CollectionTab collection = activityDao.selectCollection(userId, activityId);
        System.out.println(collection);
        result.setSuccess(true);
        result.setCode(200);
        result.setMsg("收藏结果");
        result.setData(collection);
        return result;
    }

//    收藏或取消收藏
    @PostMapping("/collectionorcancle")
    public Result CollectionOrCancle(@Param("userId") Integer userId,@Param("activityId") Integer activityId) {
        Result result = new Result();
        CollectionTab collectionTab = new CollectionTab();
        collectionTab.setActivityId(activityId);
        collectionTab.setUserId(userId);
        collectionTab.setCreateTime(new Date());
//        查询是否有收藏
        CollectionTab collection = activityDao.selectCollection(userId, activityId);
        if (collection != null) {
            //        有 删除此条收藏记录
            Integer integer = activityDao.deleteCollection(userId, activityId);
            result.setSuccess(true);
            result.setMsg("删除成功");
            result.setData(integer);
            result.setCode(200);
        }else {
            //        没有 添加收藏记录
            Integer integer = activityDao.insertCollection(collectionTab);
            result.setSuccess(true);
            result.setMsg("添加成功");
            result.setData(integer);
            result.setCode(200);
        }
        return result;
    }


//    开启签到
    @PostMapping("/signin")
    public Result SignIn(@Param("timeId") Integer timeId) {
        Result result = new Result();
        SignIn signIn = new SignIn();
        Time time = new Time();
        List<Enroll> enrollList = activityDao.getEnrollList(timeId);
        System.out.println(enrollList);
//        修改时间表的签到状态
        time.setTimeId(timeId);
        time.setIsSignIn(1);
        activityDao.updatetimetabissignin(time);
        for (int i = 0; i < enrollList.size(); i ++ ) {
//            将报名id添加到签到表中
            signIn.setSignInId(null);
            signIn.setEnrollId(enrollList.get(i).getEnrollId());
            signIn.setIsSignIn(0);
            signIn.setSignInTime(new Date());
            Integer integer = activityDao.insertSignIn(signIn);
            System.out.println(integer);
        }
        result.setSuccess(true);
        result.setCode(200);
        result.setMsg("开启签到成功");
        result.setData(1);
        return result;
    }


// 关闭签到
    @PostMapping("/closeissignin")
    public Result CloseIsSignIn(@Param("timeId")Integer timeId){
        Result result = new Result();
//        根据时间id获取报名id
        Time time = new Time();
        time.setTimeId(timeId);
        time.setIsSignIn(0);
        activityDao.updatetimetabissignin(time);
        List<Enroll> enrollList = activityDao.getEnrollList(timeId);
        int[] enrollId = new int[enrollList.size()];
        for (int i = 0; i < enrollList.size(); i++){
            enrollId[i] = enrollList.get(i).getEnrollId();
        }
        for (int i = 0; i < enrollId.length; i++){
            System.out.println(enrollId[i]);
            activityDao.DeleteSignIn(enrollId[i]);
        }
//        删除签到表的内容根据时间id
        result.setCode(200);
        result.setSuccess(true);
        result.setMsg("关闭签到成功");
        result.setData(1);
        return result;
    }




//    根据报名id查询用户是否已经签到
    @PostMapping("/issignin")
    public Result IsSignIn(@Param("enroll_id") Integer enroll_id) {
        Result result = new Result();
        SignIn signInByEnrollId = activityDao.getSignInByEnrollId(enroll_id);
        result.setSuccess(true);
        result.setCode(200);
        result.setMsg("根据报名id查询签到信息");
        result.setData(signInByEnrollId);
        return result;
    }

//    用户签到请求
    @PostMapping("/signinclick")
    public Result SignInClick(@Param("sign_in_id") Integer sign_in_id) {
        Result result = new Result();
        Integer integer = activityDao.updateSignIn(sign_in_id);
        result.setSuccess(true);
        result.setCode(200);
        result.setMsg("签到成功");
        result.setData(integer);
        return result;

    }




//    根据分类id查询活动列表
    @PostMapping("/searchlistbyclassification")
    public Result SearchListByClassification(@Param("classification_id")Integer classification_id) {
        Result result = new Result();
        List<Activity> activities = activityDao.SearchListByClassification(classification_id);
        result.setSuccess(true);
        result.setCode(200);
        result.setMsg("根据分类id获取列表");
        result.setData(activities);
        return result;
    }


//    模糊搜索活动名称
    @PostMapping("/searchactivitynamelike")
    public Result SearchActivityNameLike(@Param("name")String name) {
        Result result = new Result();
        System.out.println(name);
        List<Activity> activities = activityDao.SearchActivityNameLike(name);
        result.setSuccess(true);
        result.setCode(200);
        result.setMsg("获取搜索到的活动列表");
        result.setData(activities);
        return result;
    }




}
