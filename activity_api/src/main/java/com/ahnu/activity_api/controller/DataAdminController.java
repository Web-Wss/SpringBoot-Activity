package com.ahnu.activity_api.controller;

import com.ahnu.activity_api.dao.DataAdminDao;
import com.ahnu.activity_api.model.*;
import com.ahnu.activity_api.utils.GetSevenDate;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/dataadmin")
public class DataAdminController {

    @Autowired
    private DataAdminDao dataAdminDao;

//    获取可视化数据
    @GetMapping("/getdatalist")
    public Result GetDataList() {
        HashMap<String,Object> hashMap = new HashMap<>();
        Result result = new Result();
//        获取用户数量
        Integer userSum = dataAdminDao.GetUserSum();
        hashMap.put("userSum",userSum);
//        获取员工数量
        Integer staffSum = dataAdminDao.GetStaffSum();
        hashMap.put("staffSum",staffSum);
//        获取活动数量
        Integer activitySum = dataAdminDao.GetActivitySum();
        hashMap.put("activitySum",activitySum);
//        获取参与者数量
        Integer participantSum = dataAdminDao.GetParticipantSum();
        hashMap.put("participantSum",participantSum);
        result.setSuccess(true);
        result.setCode(200);
        result.setMsg("获取四个信息");
        result.setData(hashMap);
        return result;
    }

//    获取职工关注度
    @GetMapping("/getuserinfo")
    public Result GetUserInfo(){
        Result result = new Result();
        List<Object> list = new ArrayList();

        List<Staff> staff = dataAdminDao.GetStaffInfo();
        for (int i = 0; i < staff.size(); i ++) {
            HashMap<String,Object> hashMap = new HashMap<>();
//            根据职工id查询报名数量
            Integer staffSum = dataAdminDao.GetStaffSumInfo(staff.get(i).getStaffId());
            hashMap.put("name",staff.get(i).getStaffName());
            hashMap.put("value",staffSum);
//            System.out.println(hashMap);
            list.add(hashMap);
        }
        result.setSuccess(true);
        result.setCode(200);
        result.setMsg("获取职工受关注度数据");
        result.setData(list);
        return result;
    }


//    获取报名数量
    @GetMapping("/getenrolllist")
    public Result GetEnrollList() {
        Result result = new Result();
        HashMap<String,Object> hashMap = new HashMap<>();
        List<Activity> activities = dataAdminDao.GetActivityList();
        String[] enroll_info1 = new String[activities.size()];
        Integer[] enroll_info2 = new Integer[activities.size()];
        //        获取活动名称
        for (int i = 0; i < activities.size(); i ++) {
            enroll_info1[i] = activities.get(i).getActivityName();
        }
//            查询报名数量
        for (int i = 0; i < activities.size(); i ++) {
            Integer activityId = activities.get(i).getActivityId();
            enroll_info2[i] = dataAdminDao.GetEnrollSumList(activityId);
        }
        hashMap.put("enroll_info1",enroll_info1);
        hashMap.put("enroll_info2",enroll_info2);
        result.setCode(200);
        result.setSuccess(true);
        result.setMsg("获取报名数量echarts");
        result.setData(hashMap);
        return result;
    }


//    获取新增用户数量
    @GetMapping("/getadduserinfo")
    public Result GetAddUserInfo() {
        Result result = new Result();
        List<String> user_info1 = new ArrayList<>();
        List<Integer> user_info2 = new ArrayList<>();
        HashMap<String,Object> hashMap = new HashMap<>();
        List<String> data = GetSevenDate.getSevenDate();
//        根据日期查询新增用户数
        for (int i = data.size()-1; i >= 0; i --) {
            user_info1.add(data.get(i));
            Integer integer = dataAdminDao.GetUserSumInfo("%"+data.get(i)+"%");
            user_info2.add(integer);
        }
        hashMap.put("user_info1",user_info1);
        hashMap.put("user_info2",user_info2);
        result.setSuccess(true);
        result.setCode(200);
        result.setMsg("获取近七天新增用户数");
        result.setData(hashMap);
        return result;
    }


//    获取通知管理
    @GetMapping("/getnoticelist")
    public Result GetNoticeList(){
        Result result = new Result();
        List<Notice> notices = dataAdminDao.GetNoticeList();
        result.setCode(200);
        result.setSuccess(true);
        result.setMsg("获取通知内容");
        result.setData(notices);
        return result;
    }

//    修改通知内容
    @PostMapping("/eidtnoticecontent")
    public Result EditNoticeContent(@Param("id")Integer id,
                                    @Param("content")String content){
        Result result = new Result();
        Notice notice = new Notice();
        notice.setNoticeId(id);
        notice.setNoticeContent(content);
        Integer integer = dataAdminDao.EditNoticeContent(notice);
        result.setSuccess(true);
        result.setCode(200);
        result.setMsg("修改通知内容成功");
        result.setData(integer);
        return result;
    }


//    获取意见反馈内容
    @GetMapping("getoptionlist")
    public Result GetOptionList(){
        Result result = new Result();
        List<Option> options = dataAdminDao.GetOptionList();
        result.setCode(200);
        result.setSuccess(true);
        result.setMsg("获取意见反馈内容");
        result.setData(options);
        return result;
    }

//    删除意见反馈内容根据id
    @PostMapping("/deleteoption")
    public Result DeleteOptionById(@Param("id") Integer id){
        Result result = new Result();
        Integer integer = dataAdminDao.DeleteOptionById(id);
        result.setData(integer);
        result.setSuccess(true);
        result.setCode(200);
        result.setMsg("删除意见反馈成功");
        return result;
    }

//    获取失物招领列表
    @GetMapping("/getlostarticlelist")
    public Result GetLostArticleList(){
        Result result = new Result();
        List<LostArticle> lostArticles = dataAdminDao.GetLostArticleList();
        result.setSuccess(true);
        result.setCode(200);
        result.setMsg("获取失物招领列表");
        result.setData(lostArticles);
        return result;
    }

//    删除失物招领根据id
    @PostMapping("deletelostarticlebyid")
    public Result DeleteLostArticleById(@Param("id")Integer id,
                                        @Param("img")String img){
        Result result = new Result();
        String lostArticleImg = img;
//        删除图片
        int lastSlashIndex = lostArticleImg.lastIndexOf("/");
        String fileName = lostArticleImg.substring(lastSlashIndex + 1);
        //        要删除图片的路径
        ClassLoader classLoader = getClass().getClassLoader();
        String resourcePath = classLoader.getResource("").getPath();
        String filePath = resourcePath + "images/lost_article_img";
        File file = new File(filePath+"\\"+fileName);
        file.delete();
//        删除数据库中记录
        Integer integer = dataAdminDao.DeleteLostArticleById(id);
        result.setSuccess(true);
        result.setCode(200);
        result.setMsg("删除失物招领成功");
        result.setData(integer);
        return result;
    }



}
