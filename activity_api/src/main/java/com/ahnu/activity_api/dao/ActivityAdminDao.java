package com.ahnu.activity_api.dao;

import com.ahnu.activity_api.model.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ActivityAdminDao {

//    获取分类列表
    List<Classification> getClassificationList();

//    获取时间列表
    List<Time> getTimeList();

//    获取活动列表
    List<Activity> getActivityList();

//    获取活动列表根据分类id
    List<Activity> GetActivityListByClassificationId(@Param("classification_id")Integer classification_id);


//    添加活动分类
    Integer InsertClassification(Classification classification);


//    删除活动分类根据分类id
    Integer DeleteClassification(@Param("classification_id")Integer classification_id);

//    编辑活动分类
    Integer EditClassification(Classification classification);


//    新增活动时间
    Integer InsertTime(Time time);


//    修改活动时间内容
    Integer EditTimeInfo(Time time);

//    删除活动时间根据时间id
    Integer DeleteTime(@Param("time_id")Integer time_id);

//    创建活动
    Integer createactivity(Activity activity);
//    更新header表
    Integer InsertHeaderInfo(Header header);
    //    更新img表
    Integer InsertImgInfo(Img img);
//    更新time表
    Integer updateTimeByTimeId(Time time);

//    修改活动状态根据活动id
    Integer UpdateActivityStatusById(Activity activity);

//    修改活动信息根据id
    Integer EditActivityInfoById(Activity activity);

//    删除活动
//    根据活动id查询相关信息
    Activity SelectActivityInfoAndOther(@Param("activity_id")Integer activity_id);
//    修改time表根据时间id
    Integer UpdateTimeInfoByTImeId(@Param("activity_id")Integer activity_id);
//    删除header表信息根据活动id
    Integer DeleteHeaderInfoByActivityId(@Param("activity_id")Integer activity_id);
//    删除img表信息根据活动id
    Integer DeleteImgInfoByActivityId(@Param("activity_id")Integer activity_id);
//    删除活动
    Integer DeleteActivityInfoByActivityId(@Param("activity_id")Integer activity_id);

}
