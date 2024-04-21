package com.ahnu.activity_api.service.impl;

import com.ahnu.activity_api.dao.ActivityDao;
import com.ahnu.activity_api.model.*;
import com.ahnu.activity_api.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityDao activityDao;


    @Override
    public List<Activity> getActivityList() {
        return activityDao.getActivityList();
    }

    @Override
    public List<Classification> getClassificationList() {
        return activityDao.getClassificationList();
    }

    @Override
    public List<Time> getTimeList() {
        return activityDao.getTimeList();
    }

    @Override
    public Activity getActivityById(Integer activity_id) {
        return activityDao.getActivityById(activity_id);
    }

    @Override
    public Integer updateBrowseSum(Integer activity_id) {
        return activityDao.updateBrowseSum(activity_id);
    }

    @Override
    public Activity getActivityAndDate(Integer activity_id) {
        return activityDao.getActivityAndDate(activity_id);
    }

    @Override
    public Time getTimeListByTimeId(Integer time_id, Integer activity_id) {
        return activityDao.getTimeListByTimeId(time_id, activity_id);
    }

//    @Override
//    public Time getTimeListByTimeId(Integer time_id) {
//        return activityDao.getTimeListByTimeId(time_id);
//    }

    @Override
    public Participant getParticipantList(Integer participant_id) {
        return activityDao.getParticipantList(participant_id);
    }

    @Override
    public Users getUserInfoByUserId(Integer user_id) {
        return activityDao.getUserInfoByUserId(user_id);
    }

    @Override
    public Staff getStaffInfo(Integer staff_id) {
        return activityDao.getStaffInfo(staff_id);
    }

    @Override
    public Integer selectParticipantAndTime(Integer timeId, Integer participantId,Integer activity_id) {
        return activityDao.selectParticipantAndTime(timeId, participantId,activity_id);
    }

    @Override
    public Integer pushEnroll(Enroll enroll) {
        return activityDao.pushEnroll(enroll);
    }

    @Override
    public CollectionTab selectCollection(Integer userId, Integer activityId) {
        return activityDao.selectCollection(userId, activityId);
    }

    @Override
    public Integer insertCollection(CollectionTab collectionTab) {
        return activityDao.insertCollection(collectionTab);
    }

    @Override
    public Integer deleteCollection(Integer userId, Integer activityId) {
        return activityDao.deleteCollection(userId, activityId);
    }

    @Override
    public List<Enroll> getEnrollList(Integer timeId) {
        return activityDao.getEnrollList(timeId);
    }

    @Override
    public Integer updatetimetabissignin(Time time) {
        return activityDao.updatetimetabissignin(time);
    }

    @Override
    public Integer insertSignIn(SignIn signIn) {
        return activityDao.insertSignIn(signIn);
    }

    @Override
    public Integer DeleteSignIn(Integer enroll_id) {
        return activityDao.DeleteSignIn(enroll_id);
    }

    @Override
    public SignIn getSignInByEnrollId(Integer enroll_id) {
        return activityDao.getSignInByEnrollId(enroll_id);
    }

    @Override
    public Integer updateSignIn(Integer sign_in_id) {
        return activityDao.updateSignIn(sign_in_id);
    }

    @Override
    public List<Activity> SearchListByClassification(Integer classification_id) {
        return activityDao.SearchListByClassification(classification_id);
    }

    @Override
    public List<Activity> SearchActivityNameLike(String name) {
        return activityDao.SearchActivityNameLike(name);
    }


}
