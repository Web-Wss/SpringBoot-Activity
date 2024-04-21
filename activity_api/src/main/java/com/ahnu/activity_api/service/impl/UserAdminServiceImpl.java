package com.ahnu.activity_api.service.impl;

import com.ahnu.activity_api.dao.UserAdminDao;
import com.ahnu.activity_api.model.*;
import com.ahnu.activity_api.service.UserAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAdminServiceImpl implements UserAdminService {

    @Autowired
    private UserAdminDao userAdminDao;

    @Override
    public List<Users> GetUserList() {
        return userAdminDao.GetUserList();
    }

    @Override
    public List<Staff> GetStaffList() {
        return userAdminDao.GetStaffList();
    }

    @Override
    public List<Participant> GetParticipantList() {
        return userAdminDao.GetParticipantList();
    }

    @Override
    public List<Users> GetUserListByPhone(String phone) {
        return userAdminDao.GetUserListByPhone(phone);
    }

    @Override
    public List<Staff> GetStaffByName(String staff_name) {
        return userAdminDao.GetStaffByName(staff_name);
    }

    @Override
    public List<Participant> GetParticipantByParticipantName(String participant_name) {
        return userAdminDao.GetParticipantByParticipantName(participant_name);
    }

    @Override
    public Integer AddUserInfo(Users users) {
        return userAdminDao.AddUserInfo(users);
    }

    @Override
    public List<Participant> SearchParticipantInfoById(Integer userId) {
        return userAdminDao.SearchParticipantInfoById(userId);
    }

    @Override
    public Integer EditUserInfo(Users users) {
        return userAdminDao.EditUserInfo(users);
    }

    @Override
    public Integer UpdateParticipantByUserId(Integer userId) {
        return userAdminDao.UpdateParticipantByUserId(userId);
    }

    @Override
    public Integer EditParticipantInfo(Participant participant) {
        return userAdminDao.EditParticipantInfo(participant);
    }

    @Override
    public List<Participant> GetNoBindParticipantList() {
        return userAdminDao.GetNoBindParticipantList();
    }

    @Override
    public List<Role> GetRoleList() {
        return userAdminDao.GetRoleList();
    }

    @Override
    public Integer AddStaffInfo(Staff staff) {
        return userAdminDao.AddStaffInfo(staff);
    }

    @Override
    public Integer EditStaffInfoById(Staff staff) {
        return userAdminDao.EditStaffInfoById(staff);
    }

    @Override
    public Integer AddParticipantInfo(Participant participant) {
        return userAdminDao.AddParticipantInfo(participant);
    }

    @Override
    public Integer EditParticipantInfoById(Participant participant) {
        return userAdminDao.EditParticipantInfoById(participant);
    }

    @Override
    public List<CollectionTab> GetCollectionList() {
        return userAdminDao.GetCollectionList();
    }

    @Override
    public Integer DeleteCollectionById(Integer id) {
        return userAdminDao.DeleteCollectionById(id);
    }

    @Override
    public Integer EditStaffStatus(Staff staff) {
        return userAdminDao.EditStaffStatus(staff);
    }

    @Override
    public Integer EditUserStatus(Users users) {
        return userAdminDao.EditUserStatus(users);
    }

    @Override
    public Integer DeleteParticipantById(Integer participant_Id) {
        return userAdminDao.DeleteParticipantById(participant_Id);
    }

    @Override
    public Integer DeleteUserByUserId(Integer user_id) {
        return userAdminDao.DeleteUserByUserId(user_id);
    }

    @Override
    public Integer UpdateParticipantAndUserId(Integer user_id) {
        return userAdminDao.UpdateParticipantAndUserId(user_id);
    }

    @Override
    public List<Activity> SelectActivityAndStaffInfo() {
        return userAdminDao.SelectActivityAndStaffInfo();
    }

    @Override
    public Integer DeleteStaffInfoById(Integer staff_id) {
        return userAdminDao.DeleteStaffInfoById(staff_id);
    }
}
