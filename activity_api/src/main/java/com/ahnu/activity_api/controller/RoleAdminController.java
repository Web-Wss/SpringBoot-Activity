package com.ahnu.activity_api.controller;

import com.ahnu.activity_api.dao.RoleAdminDao;
import com.ahnu.activity_api.model.Module;
import com.ahnu.activity_api.model.Power;
import com.ahnu.activity_api.model.Result;
import com.ahnu.activity_api.model.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/roleadmin")
public class RoleAdminController {

    @Autowired
    private RoleAdminDao roleAdminDao;

//    获取角色列表
    @GetMapping("/getrolelist")
    public Result GetRoleList(){
        Result result = new Result();
        List<Role> roles = roleAdminDao.GetRoleList();
        result.setSuccess(true);
        result.setCode(200);
        result.setMsg("获取角色列表");
        result.setData(roles);
        return result;
    }

//    获取权限列表
    @GetMapping("/getpowerlist")
    public Result GetPowerList(){
        Result result = new Result();
        List<Power> powers = roleAdminDao.GetPowerList();
        result.setSuccess(true);
        result.setCode(200);
        result.setMsg("获取权限列表");
        result.setData(powers);
        return result;
    }


//    获取模块列表
    @GetMapping("/getmodulelist")
    public Result GetModuleList(){
        Result result = new Result();
        List<Module> modules = roleAdminDao.GetModuleList();
        result.setSuccess(true);
        result.setCode(200);
        result.setMsg("获取模块列表");
        result.setData(modules);
        return result;
    }


//    获取模块列表根据权限ID
    @PostMapping("/getmodulebypowerid")
    public Result GetModuleByPowerId(@Param("power_id")Integer power_id){
        Result result = new Result();

        List<Object> list = new ArrayList<>();
        System.out.println(power_id);
        Power power = roleAdminDao.GetModuleByPowerId(power_id);
//        将字符串转数组
        String moduleId = power.getModuleId();
        if (moduleId.length() > 2) {
            String[] strArray = moduleId.substring(1, moduleId.length() - 1).split(",");
            int[] intArray = new int[strArray.length];
            for (int i = 0; i < strArray.length; i++) {
                intArray[i] = Integer.parseInt(strArray[i]);
            }
            System.out.println(intArray);
            for (int i = 0; i < intArray.length; i ++) {
//            获取模块列表
                System.out.println(intArray[i]);
                Module module = roleAdminDao.GetModuleListByModuleId(intArray[i]);
                list.add(module);
            }
        }else {
//            没有对应权限
        }
        System.out.println(list);
        result.setSuccess(true);
        result.setCode(200);
        result.setData(list);
        return result;
    }


//    添加角色
    @PostMapping("/addroleinfo")
    public Result AddRoleInfo(@Param("name")String name,
                              @Param("describe")String describe,
                              @Param("powerId")Integer powerId){
        Result result = new Result();
        Role role = new Role();
        role.setRoleName(name);
        role.setRoleDescribe(describe);
        role.setPowerId(powerId);
        Integer integer = roleAdminDao.AddRoleInfo(role);
        result.setSuccess(true);
        result.setCode(200);
        result.setMsg("添加角色成功");
        result.setData(integer);
        return result;
    }


//    修改角色信息根据roleId
    @PostMapping("editroleinfobyid")
    public Result EditRoleInfoById(@Param("roleId")Integer roleId,
                                   @Param("name")String name,
                                   @Param("describe")String describe){
        Result result = new Result();
        Role role = new Role();
        role.setRoleId(roleId);
        role.setRoleName(name);
        role.setRoleDescribe(describe);
        Integer integer = roleAdminDao.EditRoleInfoById(role);
        result.setCode(200);
        result.setSuccess(true);
        result.setMsg("修改角色信息成功");
        result.setData(integer);
        return result;
    }

//    修改角色表中权限信息
    @PostMapping("/editrolepoweridinfo")
    public Result EditRolePowerIdInfo(@Param("roleId")Integer roleId,
                                      @Param("powerId")Integer powerId){
        Result result = new Result();
        Role role = new Role();
        System.out.println(roleId);
        System.out.println(powerId);
        role.setRoleId(roleId);
        role.setPowerId(powerId);
        Integer integer = roleAdminDao.EditRolePowerIdInfo(role);
        result.setSuccess(true);
        result.setCode(200);
        result.setMsg("修改powerId成功");
        result.setData(integer);
        return result;
    }


//    添加权限
    @PostMapping("/addpowerinfo")
    public Result AddPowerInfo(@Param("name")String name){
        Result result = new Result();
        Power power = new Power();
        power.setPowerName(name);
        Integer integer = roleAdminDao.AddPowerInfo(power);
        result.setCode(200);
        result.setSuccess(true);
        result.setMsg("添加权限成功");
        result.setData(integer);
        return result;
    }

//    编辑权限
    @PostMapping("/editpowerinfobyid")
    public Result EditPowerInfoById(@Param("id")Integer id,
                                    @Param("name")String name){
        Result result = new Result();
        Power power = new Power();
        power.setPowerId(id);
        power.setPowerName(name);
        Integer integer = roleAdminDao.EditPowerInfoById(power);
        result.setSuccess(true);
        result.setCode(200);
        result.setMsg("编辑权限成功");
        result.setData(integer);
        return result;
    }

//    修改权限表中模块字段
    @PostMapping("/editpowermodulelist")
    public Result EditPowerModuleList(@Param("id")Integer id,
                                      @Param("module")String module){
        Result result = new Result();
        Power power = new Power();
//        System.out.println(id);
        String module_id = "[" + module +"]";
//        System.out.println(module_id);
        power.setPowerId(id);
        power.setModuleId(module_id);
        Integer integer = roleAdminDao.EditPowerModuleList(power);
        result.setSuccess(true);
        result.setCode(200);
        result.setMsg("修改成功");
        result.setData(integer);
        return result;
    }

//    删除权限
//    第一步：查询角色表中是否有当前的权限id
    @PostMapping("/searchroleispowerid")
    public Result SearchRoleIsPowerId(@Param("power_id")Integer power_id) {
        Result result = new Result();
        Integer integer = roleAdminDao.SearchRoleIsPowerId(power_id);
        result.setCode(200);
        result.setSuccess(true);
        result.setMsg("查询角色表中是否有权限id");
        result.setData(integer);
        return result;
    }
//    第二步，删除
    @PostMapping("/deletepowerbyid")
    public Result DeletePowerById(@Param("power_id")Integer power_id) {
        Result result = new Result();
        Integer integer = roleAdminDao.DeletePowerById(power_id);
        result.setSuccess(true);
        result.setCode(200);
        result.setMsg("删除成功");
        result.setData(integer);
        return result;
    }

//    删除角色
//    第一步：查询staff表中角色id个数
    @PostMapping("/searchstaffandroleid")
    public Result SearchStaffAndRoleId(@Param("role_id")Integer role_id) {
        Result result = new Result();
        Integer integer = roleAdminDao.SearchStaffAndRoleId(role_id);
        result.setCode(200);
        result.setSuccess(true);
        result.setMsg("查询职工表中被赋角色个数");
        result.setData(integer);
        return result;
    }
//    第二步：删除
    @PostMapping("/deleterolebyid")
    public Result DeleteRoleById(@Param("role_id")Integer role_id) {
        Result result = new Result();
        Integer integer = roleAdminDao.DeleteRoleById(role_id);
        result.setSuccess(true);
        result.setCode(200);
        result.setMsg("删除角色");
        result.setData(integer);
        return result;
    }


//    根据职工权限id返回模块列表
    @PostMapping("/getmodulelistbyroleid")
    public Result GetModuleListByRoleId(@Param("role_id")Integer role_id) {
        Result result = new Result();
//        System.out.println(role_id);
//        根据角色id查询模块id
        Integer power_id = roleAdminDao.SearchModuleIdByRoleId(role_id);
//        根据权限id查询模块id列表
        String module_id = roleAdminDao.SearchModuleIdListByPowerId(power_id);
//        将module_id字符串转为数组
//        System.out.println(module_id);
        List<Module> moduleList = new ArrayList<>();
        String str = module_id;
        str = str.substring(1, str.length() - 1); //去掉字符串开头和结尾的中括号
        String[] strArray = str.split(","); //将字符串按逗号分割成字符串数组
        int[] intArray = new int[strArray.length]; //创建整型数组
        for (int i = 0; i < strArray.length; i++) {
            intArray[i] = Integer.parseInt(strArray[i]); //将字符串转换为整数
        }
        for (int i = 0; i < intArray.length; i ++){
//            根据模块id获取信息
            Module module = roleAdminDao.SearchModuleInfoByModuleId(intArray[i]);
            moduleList.add(module);
        }
        System.out.println(moduleList);
        result.setCode(200);
        result.setSuccess(true);
        result.setMsg("返回模块列表");
        result.setData(moduleList);
        return result;
    }


}
