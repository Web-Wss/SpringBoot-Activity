package com.ahnu.activity_api.service.impl;

import com.ahnu.activity_api.dao.EnrollAdminDao;
import com.ahnu.activity_api.model.Activity;
import com.ahnu.activity_api.model.Enroll;
import com.ahnu.activity_api.model.Time;
import com.ahnu.activity_api.service.EnrollAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollAdminServiceImpl implements EnrollAdminService {

    @Autowired
    private EnrollAdminDao enrollAdminDao;

    @Override
    public List<Enroll> GetEnrollList() {
        return enrollAdminDao.GetEnrollList();
    }

    @Override
    public List<Activity> GetActivityNameList() {
        return enrollAdminDao.GetActivityNameList();
    }

    @Override
    public List<Time> GetTimeListByActivityId(Integer activity_id) {
        return enrollAdminDao.GetTimeListByActivityId(activity_id);
    }

    @Override
    public List<Enroll> GetEnrollListByTimeAndActivityId(Integer activity_id, Integer time_id) {
        return enrollAdminDao.GetEnrollListByTimeAndActivityId(activity_id, time_id);
    }

    @Override
    public List<Enroll> GetParticipantName(String participant_name) {
        return enrollAdminDao.GetParticipantName(participant_name);
    }

    @Override
    public Integer DeleteEnrollById(Integer id) {
        return enrollAdminDao.DeleteEnrollById(id);
    }
}
