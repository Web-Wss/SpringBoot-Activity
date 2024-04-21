package com.ahnu.activity_api.service.impl;

import com.ahnu.activity_api.dao.RoleAdminDao;
import com.ahnu.activity_api.model.Module;
import com.ahnu.activity_api.model.Power;
import com.ahnu.activity_api.model.Role;
import com.ahnu.activity_api.service.RoleAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleAdminServiceImpl implements RoleAdminService {

    @Autowired
    private RoleAdminDao roleAdminDao;

    @Override
    public List<Role> GetRoleList() {
        return roleAdminDao.GetRoleList();
    }

    @Override
    public List<Power> GetPowerList() {
        return roleAdminDao.GetPowerList();
    }

    @Override
    public List<Module> GetModuleList() {
        return roleAdminDao.GetModuleList();
    }

    @Override
    public Power GetModuleByPowerId(Integer power_id) {
        return roleAdminDao.GetModuleByPowerId(power_id);
    }

    @Override
    public Module GetModuleListByModuleId(Integer module_id) {
        return roleAdminDao.GetModuleListByModuleId(module_id);
    }

    @Override
    public Integer AddRoleInfo(Role role) {
        return roleAdminDao.AddRoleInfo(role);
    }

    @Override
    public Integer EditRoleInfoById(Role role) {
        return roleAdminDao.EditRoleInfoById(role);
    }

    @Override
    public Integer EditRolePowerIdInfo(Role role) {
        return roleAdminDao.EditRolePowerIdInfo(role);
    }

    @Override
    public Integer AddPowerInfo(Power power) {
        return roleAdminDao.AddPowerInfo(power);
    }

    @Override
    public Integer EditPowerInfoById(Power power) {
        return roleAdminDao.EditPowerInfoById(power);
    }

    @Override
    public Integer EditPowerModuleList(Power power) {
        return roleAdminDao.EditPowerModuleList(power);
    }

    @Override
    public Integer SearchRoleIsPowerId(Integer power_id) {
        return roleAdminDao.SearchRoleIsPowerId(power_id);
    }

    @Override
    public Integer DeletePowerById(Integer power_id) {
        return roleAdminDao.DeletePowerById(power_id);
    }

    @Override
    public Integer SearchStaffAndRoleId(Integer role_id) {
        return roleAdminDao.SearchStaffAndRoleId(role_id);
    }

    @Override
    public Integer DeleteRoleById(Integer role_id) {
        return roleAdminDao.DeleteRoleById(role_id);
    }

    @Override
    public Integer SearchModuleIdByRoleId(Integer role_id) {
        return roleAdminDao.SearchModuleIdByRoleId(role_id);
    }

    @Override
    public String SearchModuleIdListByPowerId(Integer power_id) {
        return roleAdminDao.SearchModuleIdListByPowerId(power_id);
    }

    @Override
    public Module SearchModuleInfoByModuleId(Integer module_id) {
        return roleAdminDao.SearchModuleInfoByModuleId(module_id);
    }


}
