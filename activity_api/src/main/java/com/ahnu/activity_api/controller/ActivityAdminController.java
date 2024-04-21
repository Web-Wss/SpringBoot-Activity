package com.ahnu.activity_api.controller;

import com.ahnu.activity_api.dao.ActivityAdminDao;
import com.ahnu.activity_api.model.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/activityadmin")
public class ActivityAdminController {

    @Autowired
    private ActivityAdminDao activityAdminDao;

//    获取分类列表
    @GetMapping("/getclassificationlist")
    public Result GetClassificationList() {
        Result result = new Result();
        List<Classification> classificationList = activityAdminDao.getClassificationList();
        result.setCode(200);
        result.setSuccess(true);
        result.setMsg("获取分类列表");
        result.setData(classificationList);
        return result;
    }

//    获取时间列表
    @GetMapping("/gettimelist")
    public Result GetTimeList() {
        Result result = new Result();
        List<Time> timeList = activityAdminDao.getTimeList();
        result.setSuccess(true);
        result.setCode(200);
        result.setMsg("获取时间列表");
        result.setData(timeList);
        return result;
    }

//    获取活动列表
    @GetMapping("/getactivitylist")
    public Result GetActivityList() {
        Result result = new Result();
//        HashMap<String,Object> hashMap = new HashMap<>();
//        HashMap<Integer,Object> timeMap = new HashMap<>();
        List<Activity> activityList = activityAdminDao.getActivityList();
        System.out.println(activityList);
//        for (int i = 0; i < activityList.size(); i ++) {
//            String timeId = activityList.get(i).getTimeId();
//            System.out.println(timeId);
////            通过时间id查询时间段
//            if (timeId.length() > 2) {
//                String[] strArray = timeId.substring(1, timeId.length() - 1).split(",");
//                int[] intArray = new int[strArray.length];
//                for (int j = 0; j < strArray.length; j++) {
//                    intArray[j] = Integer.parseInt(strArray[j]);
//                }
//                for (int k = 0; k < intArray.length; k ++) {
////            获取时间数组
//                    Integer time_id = intArray[k];
//                    System.out.println(time_id);
//                }
//                timeMap.put(i,1);
//            }
//
//        }
//        hashMap.put("activity",activityList);
        result.setSuccess(true);
        result.setCode(200);
        result.setMsg("获取活动列表");
        result.setData(activityList);
        return result;
    }


//    获取活动列表根据分类id
    @PostMapping("/getactivitylistbyclassificationid")
    public Result GetActivityListByClassificationId(@Param("classification_id")Integer classification_id) {
        Result result = new Result();
        System.out.println(classification_id);
        List<Activity> activities = activityAdminDao.GetActivityListByClassificationId(classification_id);
        result.setSuccess(true);
        result.setCode(200);
        result.setMsg("获取根据分类id查询后的列表");
        result.setData(activities);
        return result;
    }


//    添加活动分类
    @PostMapping("/insertclassification")
    public Result InsertClassification(@Param("classification_name")String classification_name,
                                       @Param("classification_explain")String classification_explain
                                       ){
        Result result = new Result();
        Classification classification = new Classification();
        classification.setClassificationName(classification_name);
        classification.setClassificationExplain(classification_explain);
        Integer integer = activityAdminDao.InsertClassification(classification);
        result.setSuccess(true);
        result.setCode(200);
        result.setMsg("添加活动分类");
        result.setData(integer);
        return result;
    }

//    删除活动分类根据分类id
    @PostMapping("/deleteclassification")
    public Result DeleteClassification(@Param("classification_id")Integer classification_id) {
        Result result = new Result();
        Integer integer = activityAdminDao.DeleteClassification(classification_id);
        result.setSuccess(true);
        result.setCode(200);
        result.setMsg("删除分类");
        result.setData(integer);
        return result;
    }

//    修改活动分类
    @PostMapping("/editclassification")
    public Result EditClassification(@Param("classification_id")Integer classification_id,
                                     @Param("classification_name")String classification_name,
                                     @Param("classification_explain")String classification_explain
                                     ) {
        Result result = new Result();
        System.out.println(classification_id);
        System.out.println(classification_name);
        System.out.println(classification_explain);
        Classification classification = new Classification();
        classification.setClassificationId(classification_id);
        classification.setClassificationName(classification_name);
        classification.setClassificationExplain(classification_explain);
        Integer integer = activityAdminDao.EditClassification(classification);
        result.setCode(200);
        result.setSuccess(true);
        result.setMsg("修改成功");
        result.setData(integer);
        return result;
    }

//    新增活动时间
    @PostMapping("/inserttime")
    public Result InsertTime(@Param("time_name")String time_name,
                             @Param("time_content")String time_content,
                             @Param("person_sum")Integer person_sum
                             ){
        Result result = new Result();
        Time time = new Time();
        time.setTimeName(time_name);
        time.setTimeContent(time_content);
        time.setPersonSum(person_sum);
        Integer integer = activityAdminDao.InsertTime(time);
        result.setSuccess(true);
        result.setCode(200);
        result.setMsg("新增活动时间");
        result.setData(integer);
        return result;
    }

//    修改活动时间内容
    @PostMapping("/edittimeinfo")
    public Result EditTimeInfo(@Param("time_id")Integer time_id,
                               @Param("time_name")String time_name,
                               @Param("person_sum")Integer person_sum
                               ){
        Result result = new Result();
        Time time = new Time();
        time.setTimeId(time_id);
        time.setTimeName(time_name);
        time.setPersonSum(person_sum);
        Integer integer = activityAdminDao.EditTimeInfo(time);
        result.setCode(200);
        result.setSuccess(true);
        result.setMsg("修改活动时间内容");
        result.setData(integer);
        return result;
    }

//    删除时间根据id
    @PostMapping("/deletetime")
    public Result DeleteTime(@Param("time_id")Integer time_id){
        Result result = new Result();
        Integer integer = activityAdminDao.DeleteTime(time_id);
        result.setSuccess(true);
        result.setCode(200);
        result.setMsg("删除时间");
        result.setData(integer);
        return result;
    }


//    创建活动
    @PostMapping("/createactivity")
    public Result CreateActivity(@Param("name")String name,
                                 @Param("classification")Integer classification,
                                 @Param("time") Integer[] time,
                                 @Param("staff")Integer[] staff,
                                 @Param("price")String price,
                                 @Param("textarea")String textarea,
                                 @Param("xy")Integer xy,
                                 @Param("staff_id")Integer staff_id,
                                 @Param("headerimg")MultipartFile[] headerimg,
                                 @Param("imgimg")MultipartFile[] imgimg) throws IOException {
        Result result = new Result();
        Activity activity = new Activity();
        Header header = new Header();
        Img img = new Img();
        Time time1 = new Time();
        UUID uuid = UUID.randomUUID();
        System.out.println("活动名称:"+name);
        System.out.println("分类ID:"+ classification);
        String timeIdArr = "";
        for (int i = 0; i < time.length; i ++) {
            timeIdArr += time[i] + ",";
        }
        timeIdArr = "[" + timeIdArr.substring(0,timeIdArr.length()-1) +"]";
        String staffIdArr = "";
        for (int i = 0; i < staff.length; i ++) {
            staffIdArr += staff[i] + ",";
        }
        staffIdArr = "[" + staffIdArr.substring(0,staffIdArr.length()-1) + "]";
        System.out.println("时间数组："+timeIdArr);
        System.out.println("职工数组:"+staffIdArr);
        System.out.println("活动价格:"+price);
        System.out.println("活动描述:"+textarea);
        System.out.println("协议ID:"+xy);
        System.out.println("发布的职工ID："+staff_id);
        for (int i = 0; i < headerimg.length; i++) {
            System.out.println(headerimg[i].getOriginalFilename());
        }
        for (int i = 0; i < imgimg.length; i ++) {
            System.out.println(imgimg[i].getOriginalFilename());
        }
//        更新活动表
        activity.setClassificationId(classification);
        activity.setActivityName(name);
        activity.setTimeId(timeIdArr);
        activity.setActivityStaffId(staffIdArr);
        activity.setActivityDescribe(textarea);
        activity.setAgreementId(xy);
        activity.setStaffId(staff_id);
        activity.setActivityPrice(price);
        Integer createactivity = activityAdminDao.createactivity(activity);
        System.out.println("获取插入新活动的ID:"+activity.getActivityId());
        Integer newActivityId = activity.getActivityId();
//        将头图文件保存
//        获取文件名
        for (int i = 0; i < headerimg.length; i ++) {
            String originalFileName = headerimg[i].getOriginalFilename();
//            编辑文件名
            String uuidfilename = uuid + originalFileName;
//            保存文件
            ClassLoader classLoader = getClass().getClassLoader();
            String resourcePath = classLoader.getResource("").getPath();
            String filePath = resourcePath + "images/activity_header";
            File file = new File(filePath);
            if (!file.exists()){
                file.exists();
            }
//            设置图片名
            headerimg[i].transferTo(new File(filePath + "\\" + staff_id + "_" + uuidfilename));
//        更新header表
            header.setActivityId(newActivityId);
            header.setHeaderPath("http://localhost:8080/images/activity_header/"+staff_id + "_" + uuidfilename);
            Integer integer = activityAdminDao.InsertHeaderInfo(header);
        }
//        将图片文件保存
        for (int i = 0; i < imgimg.length; i ++) {
            String originalFileName = imgimg[i].getOriginalFilename();
//            编辑文件名
            String uuidfilename = uuid + originalFileName;
//            保存文件
            ClassLoader classLoader = getClass().getClassLoader();
            String resourcePath = classLoader.getResource("").getPath();
            String filePath = resourcePath + "images/activity_img";
            File file = new File(filePath);
            if (!file.exists()){
                file.exists();
            }
//            设置图片名
            imgimg[i].transferTo(new File(filePath+"\\"+staff_id + "_" +uuidfilename));
            img.setActivityId(newActivityId);
            img.setImgPath("http://localhost:8080/images/activity_img/"+staff_id+"_"+uuidfilename);
            //            更新img表
            Integer integer = activityAdminDao.InsertImgInfo(img);
        }
//        更新time表
        String oldTimeIdList = timeIdArr;
        oldTimeIdList = oldTimeIdList.substring(1,oldTimeIdList.length() - 1);
        String[] strArray = oldTimeIdList.split(",");
        int[] intArray = new int[strArray.length];
        for (int i = 0; i < strArray.length; i ++) {
            intArray[i] = Integer.parseInt(strArray[i]);
        }
        for (int i = 0; i < intArray.length; i ++) {
            System.out.println(intArray[i]);//15,16
            time1.setTimeId(intArray[i]);
            time1.setActivityId(newActivityId);
//            更新操作
            Integer integer = activityAdminDao.updateTimeByTimeId(time1);
        }
        result.setCode(200);
        result.setSuccess(true);
        result.setMsg("活动创建成功");
        result.setData(1);
        return result;
    }


//    修改活动状态根据活动id
    @PostMapping("/updateactivitystatsbyid")
    public Result UpdateActivityStatusById(@Param("activity_id")Integer activity_id,
                                           @Param("status")Integer status) {
        Result result = new Result();
        Activity activity = new Activity();
        activity.setActivityId(activity_id);
        activity.setActivityStatus(status);
//        执行修改操作
        Integer integer = activityAdminDao.UpdateActivityStatusById(activity);
        result.setSuccess(true);
        result.setCode(200);
        result.setMsg("状态修改成功");
        result.setData(integer);
        return result;
    }

//    修改活动内容信息
    @PostMapping("/editactivityinfobyid")
    public Result EditActivityInfoById(@Param("activity_id")Integer activity_id,
                                       @Param("activity_name")String activity_name,
                                       @Param("activity_describe")String activity_describe){
        Result result = new Result();
        Activity activity = new Activity();
        activity.setActivityId(activity_id);
        activity.setActivityName(activity_name);
        activity.setActivityDescribe(activity_describe);
        Integer integer = activityAdminDao.EditActivityInfoById(activity);
        result.setCode(200);
        result.setSuccess(true);
        result.setMsg("修改活动信息");
        result.setData(integer);
        return result;
    }

//    删除活动
    @PostMapping("/deleteactivitybyactivityid")
    public Result DeleteActivityByActivityId(@Param("activity_id")Integer activity_id) {
        Result result  = new Result();
//        根据活动id查询相关信息
        Activity activity = activityAdminDao.SelectActivityInfoAndOther(activity_id);
        System.out.println(activity);
//        获取时间id
        System.out.println("时间id:"+activity.getTimeId());
        String str = activity.getTimeId();
        str = str.substring(1, str.length() - 1); //去掉字符串开头和结尾的中括号
        String[] strArray = str.split(","); //将字符串按逗号分割成字符串数组
        int[] intArray = new int[strArray.length]; //创建整型数组
        for (int i = 0; i < strArray.length; i++) {
            intArray[i] = Integer.parseInt(strArray[i]); //将字符串转换为整数
        }
        for (int i = 0; i < intArray.length; i ++) {
//            执行修改时间表操作
            System.out.println("时间ID:"+intArray[i]);
//            修改time表信息改为null根据时间
//            activityAdminDao.UpdateTimeInfoByTImeId(intArray[i]);
        }
        activityAdminDao.UpdateTimeInfoByTImeId(activity_id);
//        获取header列表
        System.out.println("header列表:"+activity.getHeaderList());
        List<Header> headerList = activity.getHeaderList();
        String[] headerListArr = new String[headerList.size()];
        for (int i = 0; i < headerList.size(); i ++) {
            headerListArr[i] = headerList.get(i).getHeaderPath().substring(headerList.get(i).getHeaderPath().lastIndexOf("/")+1);
        }
        ClassLoader classLoader = getClass().getClassLoader();
        String resourcePath = classLoader.getResource("").getPath();
        String filePath = resourcePath + "images/activity_header";
        for (int i = 0; i < headerListArr.length; i ++) {
            System.out.println("header列表1:"+headerListArr[i]);
            File file = new File(filePath + "\\" + headerListArr[i]);
            file.delete();
        }
//        删除header表信息根据活动id
        activityAdminDao.DeleteHeaderInfoByActivityId(activity_id);
//        获取img列表
        System.out.println("img列表:"+activity.getImgList());
        List<Img> imgList = activity.getImgList();
        String[] imgListArr = new String[imgList.size()];
        for (int i = 0; i < imgList.size(); i ++) {
            imgListArr[i] = imgList.get(i).getImgPath().substring(imgList.get(i).getImgPath().lastIndexOf("/")+1);
        }
        ClassLoader classLoader1 = getClass().getClassLoader();
        String resourcePath1 = classLoader1.getResource("").getPath();
        String filePath1 = resourcePath1 + "images/activity_img";
        for (int i = 0; i < imgListArr.length; i ++) {
            System.out.println("img列表1:"+imgListArr[i]);
            File file = new File(filePath1 + "\\" + imgListArr[i]);
            file.delete();
        }
//        删除img表信息根据活动id
        activityAdminDao.DeleteImgInfoByActivityId(activity_id);
//        删除活动
        activityAdminDao.DeleteActivityInfoByActivityId(activity_id);
        result.setSuccess(true);
        result.setCode(200);
        result.setMsg("删除成功");
        result.setData(1);
        return result;
    }


}
