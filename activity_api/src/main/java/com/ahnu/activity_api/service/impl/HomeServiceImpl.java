package com.ahnu.activity_api.service.impl;

import com.ahnu.activity_api.dao.HomeDao;
import com.ahnu.activity_api.model.Activity;
import com.ahnu.activity_api.model.Notice;
import com.ahnu.activity_api.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class HomeServiceImpl implements HomeService {

    @Autowired
    private HomeDao homeDao;

    @Override
    public Notice getNotice() {
        return homeDao.getNotice();
    }

    @Override
    public List<Activity> getActivityList() {
        return homeDao.getActivityList();
    }
}
