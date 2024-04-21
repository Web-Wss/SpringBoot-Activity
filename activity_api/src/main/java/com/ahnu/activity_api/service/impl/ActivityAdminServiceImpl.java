package com.ahnu.activity_api.service.impl;

import com.ahnu.activity_api.dao.ActivityAdminDao;
import com.ahnu.activity_api.model.*;
import com.ahnu.activity_api.service.ActivityAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityAdminServiceImpl implements ActivityAdminService {

    @Autowired
    private ActivityAdminDao activityAdminDao;


    @Override
    public List<Classification> getClassificationList() {
        return activityAdminDao.getClassificationList();
    }

    @Override
    public List<Time> getTimeList() {
        return activityAdminDao.getTimeList();
    }

    @Override
    public List<Activity> getActivityList() {
        return activityAdminDao.getActivityList();
    }

    @Override
    public List<Activity> GetActivityListByClassificationId(Integer classification_id) {
        return activityAdminDao.GetActivityListByClassificationId(classification_id);
    }

    @Override
    public Integer InsertClassification(Classification classification) {
        return activityAdminDao.InsertClassification(classification);
    }

    @Override
    public Integer DeleteClassification(Integer classification_id) {
        return activityAdminDao.DeleteClassification(classification_id);
    }

    @Override
    public Integer EditClassification(Classification classification) {
        return activityAdminDao.EditClassification(classification);
    }

    @Override
    public Integer InsertTime(Time time) {
        return activityAdminDao.InsertTime(time);
    }

    @Override
    public Integer EditTimeInfo(Time time) {
        return activityAdminDao.EditTimeInfo(time);
    }

    @Override
    public Integer DeleteTime(Integer time_id) {
        return activityAdminDao.DeleteTime(time_id);
    }

    @Override
    public Integer createactivity(Activity activity) {
        return activityAdminDao.createactivity(activity);
    }

    @Override
    public Integer InsertHeaderInfo(Header header) {
        return activityAdminDao.InsertHeaderInfo(header);
    }

    @Override
    public Integer InsertImgInfo(Img img) {
        return activityAdminDao.InsertImgInfo(img);
    }

    @Override
    public Integer updateTimeByTimeId(Time time) {
        return activityAdminDao.updateTimeByTimeId(time);
    }

    @Override
    public Integer UpdateActivityStatusById(Activity activity) {
        return activityAdminDao.UpdateActivityStatusById(activity);
    }

    @Override
    public Integer EditActivityInfoById(Activity activity) {
        return activityAdminDao.EditActivityInfoById(activity);
    }

    @Override
    public Activity SelectActivityInfoAndOther(Integer activity_id) {
        return activityAdminDao.SelectActivityInfoAndOther(activity_id);
    }

    @Override
    public Integer UpdateTimeInfoByTImeId(Integer activity_id) {
        return activityAdminDao.UpdateTimeInfoByTImeId(activity_id);
    }

    @Override
    public Integer DeleteHeaderInfoByActivityId(Integer activity_id) {
        return activityAdminDao.DeleteHeaderInfoByActivityId(activity_id);
    }

    @Override
    public Integer DeleteImgInfoByActivityId(Integer activity_id) {
        return activityAdminDao.DeleteImgInfoByActivityId(activity_id);
    }

    @Override
    public Integer DeleteActivityInfoByActivityId(Integer activity_id) {
        return activityAdminDao.DeleteActivityInfoByActivityId(activity_id);
    }
}
