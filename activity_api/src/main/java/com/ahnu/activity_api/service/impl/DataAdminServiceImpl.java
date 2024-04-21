package com.ahnu.activity_api.service.impl;

import com.ahnu.activity_api.dao.DataAdminDao;
import com.ahnu.activity_api.model.*;
import com.ahnu.activity_api.service.DataAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataAdminServiceImpl implements DataAdminService {

    @Autowired
    private DataAdminDao dataAdminDao;

    @Override
    public Integer GetUserSum() {
        return dataAdminDao.GetUserSum();
    }

    @Override
    public Integer GetStaffSum() {
        return dataAdminDao.GetStaffSum();
    }

    @Override
    public Integer GetActivitySum() {
        return dataAdminDao.GetActivitySum();
    }

    @Override
    public Integer GetParticipantSum() {
        return dataAdminDao.GetParticipantSum();
    }

    @Override
    public List<Staff> GetStaffInfo() {
        return dataAdminDao.GetStaffInfo();
    }

    @Override
    public Integer GetStaffSumInfo(Integer staff_id) {
        return dataAdminDao.GetStaffSumInfo(staff_id);
    }

    @Override
    public List<Activity> GetActivityList() {
        return dataAdminDao.GetActivityList();
    }

    @Override
    public Integer GetEnrollSumList(Integer activity_id) {
        return dataAdminDao.GetEnrollSumList(activity_id);
    }

    @Override
    public Integer GetUserSumInfo(String user_time) {
        return dataAdminDao.GetUserSumInfo(user_time);
    }

    @Override
    public List<Notice> GetNoticeList() {
        return dataAdminDao.GetNoticeList();
    }

    @Override
    public Integer EditNoticeContent(Notice notice) {
        return dataAdminDao.EditNoticeContent(notice);
    }

    @Override
    public List<Option> GetOptionList() {
        return dataAdminDao.GetOptionList();
    }

    @Override
    public Integer DeleteOptionById(Integer id) {
        return dataAdminDao.DeleteOptionById(id);
    }

    @Override
    public List<LostArticle> GetLostArticleList() {
        return dataAdminDao.GetLostArticleList();
    }

    @Override
    public Integer DeleteLostArticleById(Integer id) {
        return dataAdminDao.DeleteLostArticleById(id);
    }


}
