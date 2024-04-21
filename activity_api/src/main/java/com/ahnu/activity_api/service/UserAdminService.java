package com.ahnu.activity_api.service;

import com.ahnu.activity_api.model.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserAdminService {

    //    获取用户列表
    List<Users> GetUserList();

    //    获取职工列表
    List<Staff> GetStaffList();

    //    获取参与者列表
    List<Participant> GetParticipantList();

    //    搜索用户
    List<Users> GetUserListByPhone(@Param("phone")String phone);

    //    搜索员工根据员工名称
    List<Staff> GetStaffByName(@Param("staff_name")String staff_name);

    //    搜索参与者
    List<Participant> GetParticipantByParticipantName(@Param("participant_name")String participant_name);

    //    添加用户信息
    Integer AddUserInfo(Users users);

    //    根据用户id查询参与者信息
    List<Participant> SearchParticipantInfoById(@Param("userId")Integer userId);

    //    修改用户信息
    Integer EditUserInfo(Users users);

    //    将参与者表的用户id字段修改为null
    Integer UpdateParticipantByUserId(@Param("userId")Integer userId);
    //     修改参与者表的用户id信息
    Integer EditParticipantInfo(Participant participant);

    //    获取未被绑定的参与者列表
    List<Participant> GetNoBindParticipantList();


    //    获取角色列表
    List<Role> GetRoleList();

    //    添加职工信息
    Integer AddStaffInfo(Staff staff);

    //    修改职工信息根据id
    Integer EditStaffInfoById(Staff staff);


    //    添加参与者信息
    Integer AddParticipantInfo(Participant participant);


    //    修改参与者信息
    Integer EditParticipantInfoById(Participant participant);


    //    获取收藏列表
    List<CollectionTab> GetCollectionList();

    //    删除收藏根据id
    Integer DeleteCollectionById(Integer id);

    //    修改职工的状态
    Integer EditStaffStatus(Staff staff);

    //    修改用户的状态
    Integer EditUserStatus(Users users);

    //    删除参与者根据参与者id
    Integer DeleteParticipantById(@Param("participant_id")Integer participant_Id);


    //    删除用户根据用户id
    Integer DeleteUserByUserId(@Param("user_id")Integer user_id);
    Integer UpdateParticipantAndUserId(@Param("user_id")Integer user_id);

    //    删除职工
//    获取所有活动列表中的职工信息
    List<Activity> SelectActivityAndStaffInfo();
    //    删除数据库记录
    Integer DeleteStaffInfoById(@Param("staff_id")Integer staff_id);

}
