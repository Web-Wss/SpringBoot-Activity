package com.ahnu.activity_api.service;

import com.ahnu.activity_api.model.Module;
import com.ahnu.activity_api.model.Power;
import com.ahnu.activity_api.model.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleAdminService {

    //    获取角色列表
    List<Role> GetRoleList();

    //    获取权限列表
    List<Power> GetPowerList();

    //    获取模块列表
    List<Module> GetModuleList();

    //    获取模块列表根据权限ID
//    根据权限id获取模块列表
    Power GetModuleByPowerId(@Param("power_id")Integer power_id);
    //    获取模块列表根据权限ID
    Module GetModuleListByModuleId(@Param("module_id")Integer module_id);


    //    添加角色
    Integer AddRoleInfo(Role role);

    //    修改角色信息根据roleId
    Integer EditRoleInfoById(Role role);

    //    修改角色表中权限信息
    Integer EditRolePowerIdInfo(Role role);


    //    添加权限
    Integer AddPowerInfo(Power power);

    //    编辑权限
    Integer EditPowerInfoById(Power power);

    //    修改权限表中的模块字段
    Integer EditPowerModuleList(Power power);

    //    删除权限
//    第一步：查询角色表中是否有当前的权限id
    Integer SearchRoleIsPowerId(@Param("power_id")Integer power_id);
    //    第二步：删除
    Integer DeletePowerById(@Param("power_id")Integer power_id);

    //    删除角色
//    第一步：查询staff表中角色id个数
    Integer SearchStaffAndRoleId(@Param("role_id")Integer role_id);
    //    第二步：删除
    Integer DeleteRoleById(@Param("role_id")Integer role_id);

    //    根据角色id查询模块id
    Integer SearchModuleIdByRoleId(@Param("role_id")Integer role_id);

    //    根据权限id查询模块id列表
    String SearchModuleIdListByPowerId(@Param("power_id")Integer power_id);
    //    根据模块id获取相关信息
    Module SearchModuleInfoByModuleId(@Param("module_id")Integer module_id);
}
