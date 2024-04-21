package com.ahnu.activity_api.controller;


import com.ahnu.activity_api.dao.UserAdminDao;
import com.ahnu.activity_api.model.*;
import org.apache.catalina.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/useradmin")
public class UserAdminController {

    @Autowired
    private UserAdminDao userAdminDao;

//    获取用户列表
    @GetMapping("/getuserlist")
    public Result GetUserList() {
        Result result = new Result();
        List<Users> users = userAdminDao.GetUserList();
        result.setCode(200);
        result.setSuccess(true);
        result.setMsg("获取用户列表");
        result.setData(users);
        return result;
    }

//    获取职工列表
    @GetMapping("/getstafflist")
    public Result GetStaffList(){
        Result result = new Result();
        List<Staff> staff = userAdminDao.GetStaffList();
        result.setSuccess(true);
        result.setCode(200);
        result.setMsg("获取职工列表");
        result.setData(staff);
        return result;
    }


//    获取参与者列表
    @GetMapping("/getparticipantlist")
    public Result GetParticipantList() {
        Result result = new Result();
        List<Participant> participantList = userAdminDao.GetParticipantList();
        result.setCode(200);
        result.setSuccess(true);
        result.setMsg("获取参与者列表");
        result.setData(participantList);
        return result;
    }


//    搜索用户
    @PostMapping("/getuserlistbyphone")
    public Result GetUserListByPhone(@Param("phone")String phone) {
        Result result = new Result();
        List<Users> users = userAdminDao.GetUserListByPhone(phone);
        result.setCode(200);
        result.setSuccess(true);
        result.setMsg("搜索用户");
        result.setData(users);
        return result;
    }

//    搜索员工根据员工名称
    @PostMapping("/getstaffbyname")
    public Result GetStaffByName(@Param("staff_name")String staff_name) {
        Result result = new Result();
        List<Staff> staff = userAdminDao.GetStaffByName(staff_name);
        result.setSuccess(true);
        result.setCode(200);
        result.setMsg("搜索员工");
        result.setData(staff);
        return result;
    }


//    查询参与者根据参与者姓名
    @PostMapping("/getparticipantbyparticipantname")
    public Result GetParticipantByParticipantName(@Param("participant_name")String participant_name){
        Result result = new Result();
        List<Participant> participantList = userAdminDao.GetParticipantByParticipantName(participant_name);
        result.setSuccess(true);
        result.setCode(200);
        result.setMsg("搜索参与者");
        result.setData(participantList);
        return result;
    }


//    添加用户
    @PostMapping("/adduserinfo")
    public Result AddUserInfo(@Param("username")String username,
                              @Param("password")String password,
                              @Param("parentName")String parentName,
                              @Param("phone")String phone){
        Result result = new Result();
        Users users = new Users();
        users.setUsername(username);
        users.setPassword(password);
        users.setParentName(parentName);
        users.setPhone(phone);
        Integer integer = userAdminDao.AddUserInfo(users);
        result.setSuccess(true);
        result.setCode(200);
        result.setMsg("添加用户成功");
        result.setData(integer);
        return result;
    }

//    根据用户id查询参与者信息
    @PostMapping("/searchparticipantinfobyid")
    public Result SearchParticipantInfoById(@Param("userId")Integer userId){
        Result result = new Result();
        List<Participant> participantList = userAdminDao.SearchParticipantInfoById(userId);
        result.setSuccess(true);
        result.setCode(200);
        result.setMsg("根据参与者id获取参与者信息");
        result.setData(participantList);
        return result;
    }
//    修改用户信息
    @PostMapping("/edituserinfo")
    public Result EditUserInfo(@Param("username")String username,
                               @Param("password")String password,
                               @Param("parentName")String parentName,
                               @Param("phone")String phone,
                               @Param("userId")Integer userId,
                               @Param("participantId")String[] participantId){
        Result result = new Result();
        Users users = new Users();
        Participant participant = new Participant();
        users.setUserId(userId);
        users.setUsername(username);
        users.setPassword(password);
        users.setParentName(parentName);
        users.setPhone(phone);
        String id = "";
        if (participantId.length > 0) {
            for (int i = 0; i < participantId.length; i ++) {
                id += participantId[i] + ",";
            }
            String ids = id.substring(0,id.length()-1);
            String participantIds = "[" + ids + "]";
            users.setParticipantId(participantIds);
        }else{
            users.setParticipantId("["+id+"]");
        }
        System.out.println(username);
        System.out.println(password);
        System.out.println(parentName);
        System.out.println(phone);
        System.out.println(userId);
//        修改用户表的参与者字段
        Integer integer = userAdminDao.EditUserInfo(users);
//        将参与者表的用户id字段修改为null
        Integer integer2 = userAdminDao.UpdateParticipantByUserId(userId);
//        修改参与者表的用户id字段
        Integer participant_id = 0;
        for (int i = 0; i < participantId.length; i ++){
            participant_id = Integer.valueOf(participantId[i]);
            participant.setUserId(userId);
            participant.setParticipantId(participant_id);
//            执行修改
            System.out.println(participant_id);
            Integer integer1 = userAdminDao.EditParticipantInfo(participant);
        }
        result.setCode(200);
        result.setSuccess(true);
        result.setMsg("修改成功");
        result.setData(integer);
        return result;
    }

//    获取未被绑定的参与者列表
    @GetMapping("/getnobindparticipantlist")
    public Result GetNoBindParticipantList(){
        Result result = new Result();
        List<Participant> participantresult = new ArrayList<>();
        List<Participant> participantList = userAdminDao.GetNoBindParticipantList();
        System.out.println(participantList);
        for (int i = 0; i < participantList.size(); i ++){
            if (participantList.get(i).getUserId() == null) {
                participantresult.add(participantList.get(i));
            }
        }
        result.setCode(200);
        result.setSuccess(true);
        result.setMsg("获取未绑定用户的参与者列表");
        result.setData(participantresult);

        return result;
    }


//    获取角色列表
    @GetMapping("/getrolelist")
    public Result GetRoleList(){
        Result result = new Result();
        List<Role> roles = userAdminDao.GetRoleList();
        result.setCode(200);
        result.setSuccess(true);
        result.setMsg("获取角色列表");
        result.setData(roles);
        return result;
    }


//    添加员工
    @PostMapping("/addstaffinfo")
    public Result AddStaffInfo(@Param("name")String name,
                               @Param("card")String card,
                               @Param("phone")String phone,
                               @Param("role")Integer role,
                               @Param("ms")String ms,
                               @Param("password")String password,
                               @Param("avatar") MultipartFile uploadFile) throws IOException {
        Result result = new Result();
        Staff staff = new Staff();
        staff.setStaffName(name);
        staff.setStaffCard(card);
        staff.setStaffPhone(phone);
        staff.setRoleId(role);
        staff.setStaffInfo(ms);
        staff.setPassword(password);
        UUID uuid = UUID.randomUUID();
        String originalFilename = uploadFile.getOriginalFilename();
        String uuidfilename = uuid + originalFilename;
        ClassLoader classLoader = getClass().getClassLoader();
        String resourcePath = classLoader.getResource("").getPath();
        String filePath = resourcePath + "images/staff_avatar";
        File file = new File(filePath);
        System.out.println(filePath);
        if (!file.exists()) {
            file.mkdirs();
        }
        uploadFile.transferTo(new File(filePath + "\\" + uuidfilename));
        staff.setStaffAvatar("http://localhost:8080/images/staff_avatar/"+uuidfilename);
//        修改数据库信息
        System.out.println(staff);
        Integer integer = userAdminDao.AddStaffInfo(staff);
        result.setSuccess(true);
        result.setCode(200);
        result.setMsg("添加用户成功");
        result.setData(integer);
        return result;
    }


//    修改职工信息
    @PostMapping("/editstaffinfobyid")
    public Result EditStaffInfoById(@Param("id")Integer id,
                                    @Param("name")String name,
                                    @Param("card")String card,
                                    @Param("phone")String phone,
                                    @Param("role")Integer role,
                                    @Param("ms")String ms,
                                    @Param("oldavatar")String oldavatar,
                                    @Param("avatar") MultipartFile uploadFile
                                    ) throws IOException {
        Result result = new Result();
        UUID uuid = UUID.randomUUID();
        Staff staff = new Staff();
        staff.setStaffName(name);
        staff.setStaffCard(card);
        staff.setStaffPhone(phone);
        staff.setRoleId(role);
        staff.setStaffInfo(ms);
//        获取前端传来的用户头像
//        删除原来的头像文件
        ClassLoader classLoader = getClass().getClassLoader();
        String resourcePath = classLoader.getResource("").getPath();
        String filePath = resourcePath + "images/staff_avatar";
        File file = new File(filePath+"\\"+oldavatar);
        System.out.println(filePath+"\\"+oldavatar);
        file.delete();
//        修改数据库数据
        String originalFilename = uploadFile.getOriginalFilename();
        String uuidfilename = uuid + originalFilename;
//        保存新头像
        uploadFile.transferTo(new File(filePath+"\\"+uuidfilename));
//        修改数据库内容
        staff.setStaffId(id);
        staff.setStaffName(name);
        staff.setStaffCard(card);
        staff.setStaffPhone(phone);
        staff.setRoleId(role);
        staff.setStaffInfo(ms);
        staff.setStaffAvatar("http://localhost:8080/images/staff_avatar/"+uuidfilename);
        Integer integer = userAdminDao.EditStaffInfoById(staff);
        result.setCode(200);
        result.setSuccess(true);
        result.setMsg("修改职工信息成功");
        result.setData(integer);
        return result;
    }



//    添加参与者
    @PostMapping("/addparticipantinfo")
    public Result AddParticipantInfo(@Param("name")String name,
                                     @Param("card")String card,
                                     @Param("height")String height,
                                     @Param("weight")String weight,
                                     @Param("sex")String sex){
        Result result = new Result();
        Participant participant = new Participant();
        participant.setParticipantName(name);
        participant.setParticipantCard(card);
        participant.setParticipantHeight(height);
        participant.setParticipantWeight(weight);
        participant.setParticipantSex(sex);
        Integer integer = userAdminDao.AddParticipantInfo(participant);
        result.setSuccess(true);
        result.setCode(200);
        result.setMsg("添加参与者成功");
        result.setData(integer);
        return result;
    }


//    修改参与者信息
    @PostMapping("/editparticipantinfobyid")
    public Result EditParticipantInfoById(@Param("id")Integer id,
                                          @Param("name")String name,
                                          @Param("height")String height,
                                          @Param("weight")String weight,
                                          @Param("card")String card,
                                          @Param("sex")String sex){
        Result result = new Result();
        Participant participant = new Participant();
        participant.setParticipantId(id);
        participant.setParticipantName(name);
        participant.setParticipantHeight(height);
        participant.setParticipantWeight(weight);
        participant.setParticipantCard(card);
        participant.setParticipantSex(sex);
        Integer integer = userAdminDao.EditParticipantInfoById(participant);
        result.setCode(200);
        result.setSuccess(true);
        result.setMsg("修改参与者信息成功");
        result.setData(integer);
        return result;
    }


//    获取收藏列表
    @GetMapping("/getcollectionlist")
    public Result GetCollectionList(){
        Result result = new Result();
        List<CollectionTab> collectionTabs = userAdminDao.GetCollectionList();
        result.setSuccess(true);
        result.setCode(200);
        result.setMsg("获取收藏列表");
        result.setData(collectionTabs);
        return result;
    }

//    删除收藏根据id
    @PostMapping("/deletecollectionbyid")
    public Result DeleteCollectionById(@Param("id")Integer id){
        Result result = new Result();
        Integer integer = userAdminDao.DeleteCollectionById(id);
        result.setSuccess(true);
        result.setCode(200);
        result.setMsg("删除成功");
        result.setData(integer);
        return result;
    }

    //    修改用户的状态
    @PostMapping("/edituserstatus")
    public Result EditUserStatus(@Param("user_id")Integer user_id,
                                  @Param("status")Integer status) {
        Result result = new Result();
        Users users = new Users();
        users.setUserId(user_id);
        users.setUserStatus(status);
        Integer integer = userAdminDao.EditUserStatus(users);
        result.setSuccess(true);
        result.setCode(200);
        result.setMsg("修改成功");
        result.setData(integer);
        return result;
    }

    //    修改职工的状态
    @PostMapping("/editstaffstatus")
    public Result EditStaffStatus(@Param("staff_id")Integer staff_id,
                                  @Param("status")String status) {
        Result result = new Result();
        Staff staff = new Staff();
        System.out.println(staff_id);
        System.out.println(status);
        staff.setStaffId(staff_id);
        staff.setStaffStatus(status);
        Integer integer = userAdminDao.EditStaffStatus(staff);
        result.setSuccess(true);
        result.setCode(200);
        result.setMsg("修改成功");
        result.setData(integer);
        return result;
    }


//    删除参与者根据参与者id
    @PostMapping("/deleteparticipantbyid")
    public Result DeleteParticipantById(@Param("participant_id")Integer participant_id) {
        Result result = new Result();
        Integer integer = userAdminDao.DeleteParticipantById(participant_id);
        result.setCode(200);
        result.setSuccess(true);
        result.setMsg("删除参与者成功");
        result.setData(integer);
        return result;
    }


//    删除用户根据用户id
    @PostMapping("/deleteuserbyuserid")
    public Result DeleteUserByUserId(@Param("user_id")Integer user_id) {
        Result result = new Result();
//        删除用户表中数据
        Integer integer = userAdminDao.DeleteUserByUserId(user_id);
//        修改参与者表中的数据
        Integer integer1 = userAdminDao.UpdateParticipantAndUserId(user_id);
        result.setSuccess(true);
        result.setCode(200);
        result.setMsg("删除用户成功");
        result.setData(integer);
        return result;
    }

//    删除职工
    @PostMapping("/deletestaffinfobyid")
    public Result DeleteStaffInfoById(@Param("staff_id")Integer staff_id,
                                      @Param("avatar")String avatar) {
        Result result = new Result();
//        获取所有活动列表中的职工信息
        String staffIdList = "";
        List<Activity> activities = userAdminDao.SelectActivityAndStaffInfo();
        for (int i = 0; i < activities.size(); i ++) {
            staffIdList += activities.get(i).getActivityStaffId();
        }
        System.out.println(staffIdList);
//        判断此职工是否被引用
        Boolean IsYy = false;
        String[] strArray = staffIdList.split("\\[|\\]|,"); //使用正则表达式将字符串按照"[","]"和","分割
        int[] intArray = new int[strArray.length]; //创建整型数组
        int index = 0;
        for (String s : strArray) {
            if (!s.equals("")) { //跳过空字符串
                intArray[index++] = Integer.parseInt(s); //将非空字符串转换为整数
            }
        }
        int[] result1 = Arrays.copyOf(intArray, index); //将整型数组的长度调整为有效元素的个数
        for (int i = 0; i < result1.length; i ++) {
            System.out.println(result1[i]);
            if (result1[i] == 3) {
                IsYy = true;
                continue;
            }
        }
        System.out.println(IsYy);
//        如果IsYy是true，不能删除，反之，删除
        if (IsYy == true) {
//            不能删除
            result.setCode(200);
            result.setSuccess(true);
            result.setMsg("不能删除");
            result.setData(0);
        }else{
//            执行删除
            if (avatar == "http://localhost:8080/images/staff_avatar/github.png") {
//                直接删除数据库
                Integer integer = userAdminDao.DeleteStaffInfoById(staff_id);
            }else {
//                删除文件
                int lastIndex = avatar.lastIndexOf("/"); //获取最后一个斜杠的索引
                String result2 = avatar.substring(lastIndex + 1); //获取最后一个斜杠后的字符串
                System.out.println(result2); //输出结果为"file.txt"
                ClassLoader classLoader = getClass().getClassLoader();
                String resourcePath = classLoader.getResource("").getPath();
                String filePath = resourcePath + "images/staff_avatar";
                File file = new File(filePath+"\\"+result2);
                file.delete();
//                删除数据库
                Integer integer = userAdminDao.DeleteStaffInfoById(staff_id);
            }
            result.setCode(200);
            result.setSuccess(true);
            result.setMsg("删除职工");
            result.setData(1);
        }
        return result;
    }

}
