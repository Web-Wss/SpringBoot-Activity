package com.ahnu.activity_api.service.impl;

import com.ahnu.activity_api.dao.SignInAdminDao;
import com.ahnu.activity_api.model.SignIn;
import com.ahnu.activity_api.service.SignInAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SignInAdminServiceImpl implements SignInAdminService {

    @Autowired
    private SignInAdminDao signInAdminDao;

    @Override
    public List<SignIn> GetSignInList() {
        return signInAdminDao.GetSignInList();
    }

    @Override
    public List<SignIn> GetSignInListByTimeIdAndActivityId(Integer activity_id, Integer time_id) {
        return signInAdminDao.GetSignInListByTimeIdAndActivityId(activity_id, time_id);
    }
}
