package com.ahnu.activity_api.controller;


import com.ahnu.activity_api.dao.MyDao;
import com.ahnu.activity_api.model.*;
import com.ahnu.activity_api.service.impl.MyServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/my")
public class MyController {

    @Autowired
    private MyDao myDao;


//    获取报名信息
    @PostMapping ("/enrollinfo")
    public Result getEnrollInfo(Integer user_id) {
        Result result = new Result();
        List<Enroll> enrollList = myDao.getEnrollList(user_id);
        result.setCode(200);
        result.setMsg("报名列表");
        result.setSuccess(true);
        result.setData(enrollList);
        return result;
    }

//提交签名
    @PostMapping("/uploadresultimg")
    public Result UploadResultImg(@Param("enroll_id")Integer enroll_id,
                                  @Param("result_img")String result_img,
                                  @Param("agreement_id")Integer agreement_id){
        Result result = new Result();
        Contract contract = new Contract();
        Enroll enroll = new Enroll();
        contract.setEnrollId(enroll_id);
        contract.setAgreementId(agreement_id);
        contract.setAutographImg(result_img);
        Integer integer = myDao.UploadResultImg(contract);
        if (integer == 1) {
//            修改报名表中的合同字段
            enroll.setEnrollId(enroll_id);
            enroll.setContractId(contract.getContractId());
            Integer integer1 = myDao.updateEnrollInfoAndContractId(enroll);
        }
        result.setCode(200);
        result.setSuccess(true);
        result.setMsg("签名提交成功");
        result.setData(integer);
        return result;
    }

//    查看电子签名
    @PostMapping("/searchcontractinfoimg")
    public Result SearchContractIngoImg(@Param("contract_id")Integer contract_id) {
        Result result = new Result();
        Contract contract = myDao.SearchContractIngoImg(contract_id);
        result.setSuccess(true);
        result.setCode(200);
        result.setMsg("获取合同信息根据id");
        result.setData(contract);
        return result;
    }

//    根据协议id获取协议内容
    @PostMapping("/getagreementcontentbyid")
    public Result GetAgreementContentById(@Param("agreement_id")Integer agreement_id) {
        Result result = new Result();
        Agreement agreement = myDao.GetAgreementContentById(agreement_id);
        result.setCode(200);
        result.setSuccess(true);
        result.setMsg("获取协议内容");
        result.setData(agreement);
        return result;
    }


//    获取我的收藏
    @PostMapping("/collection")
    public Result getCollectionList(Integer user_id) {
        Result result = new Result();
        List<CollectionTab> collectionList = myDao.getCollectionList(user_id);
        result.setSuccess(true);
        result.setCode(200);
        result.setMsg("获取我收藏的列表");
        result.setData(collectionList);
        return result;
    }

//    删除收藏
    @PostMapping("/deletecollection")
    public Result DeleteCollection(@Param("collection_id")Integer collection_id) {
        Result result = new Result();
        Integer integer = myDao.DeleteCollection(collection_id);
        result.setSuccess(true);
        result.setCode(200);
        result.setMsg("收藏删除成功");
        result.setData(integer);

        return result;
    }



//    根据用户id获取参与者列表
    @PostMapping("/participantlist")
    public Result GetParticipantList(@Param("user_id") Integer user_id) {
        Result result = new Result();
        List<Participant> participantList = new ArrayList<>();
        String participantId = myDao.getParticipantId(user_id);
        if (participantId.length() > 2) {
            String[] strArray1 = participantId.substring(1, participantId.length() - 1).split(",");
            int[] intArray1 = new int[strArray1.length];
            for (int i = 0; i < strArray1.length; i++) {
                intArray1[i] = Integer.parseInt(strArray1[i]);
            }
            for (int i = 0; i < intArray1.length; i ++) {
//            获取参与者
                Integer participant_id = intArray1[i];
                Participant participantInfo = myDao.getParticipantList(participant_id);
                participantList.add(participantInfo);
            }
        }
        result.setCode(200);
        result.setSuccess(true);
        result.setMsg("参与者列表");
        result.setData(participantList);
        return result;
    }

//    删除参与者，更新操作
    @PostMapping("/deleteparticipant")
    public Result DeleteParticipant(@Param("participantId") Integer participantId,@Param("user_id") Integer user_id,@Param("participant_id") String participant_id) {
        Result result = new Result();
        System.out.println("user_id"+user_id);
        System.out.println("participant_id"+participant_id);
        System.out.println("participantId"+participantId);
//        修改用户绑定的参与者
        Integer integer = myDao.deleteParticipant(user_id, participant_id);
//        删除参与者
        Integer integer1 = myDao.deleteParticipantByPId(participantId);
        result.setSuccess(true);
        result.setCode(200);
        result.setMsg("删除成功");
        result.setData(integer);
        return result;
    }

//    绑定参与者
    @PostMapping("/bind")
    public Result Bind(@Param("name") String name,
                       @Param("card") String card,
                       @Param("height") String height,
                       @Param("weight") String weight,
                       @Param("sex") String sex,
                       @Param("user_id") Integer user_id
                       ) {
        Result result = new Result();
        Participant participant = new Participant();
        participant.setParticipantName(name);
        participant.setParticipantCard(card);
        participant.setParticipantHeight(height);
        participant.setParticipantWeight(weight);
        participant.setParticipantSex(sex);
        participant.setUserId(user_id);
//        添加参与者
        Integer integer = myDao.insertParticipant(participant);
        if (integer == 1) {
//            获取用户的参与者id
            String participantId = myDao.getParticipantId(user_id);
            if (participantId.length() > 2) {
                String[] strArray1 = participantId.substring(1, participantId.length() - 1).split(",");
                int[] intArray1 = new int[strArray1.length + 1];
                for (int i = 0; i < strArray1.length; i++) {
                    intArray1[i] = Integer.parseInt(strArray1[i]);
                }
                intArray1[strArray1.length] = participant.getParticipantId();
                String[] strArray = Arrays.toString(intArray1).split("[\\[\\]]")[1].split(",");
//            成功，修改用户参与者信息
                String participant_id = Arrays.toString(strArray).replaceAll("\\s+","");
                Integer integer1 = myDao.updateParticipant(user_id, participant_id);
            }else {
                Integer participantId1 = participant.getParticipantId();
                String participant_id = "[" + participantId1 + "]";
                myDao.updateParticipant(user_id,participant_id);
            }
            result.setCode(200);
            result.setSuccess(true);
            result.setMsg("绑定成功");
            result.setData(null);
        }
        return result;
    }


//    提交反馈意见
    @PostMapping("/option")
    public Result Option(@Param("user_id") Integer user_id,@Param("message") String message) {
        Result result = new Result();
        Option option = new Option();
        option.setUserId(user_id);
        option.setOptionId(null);
        option.setOptionContent(message);
        option.setOptionTime(new Date());
        Integer integer = myDao.insertOption(option);
        result.setCode(200);
        result.setSuccess(true);
        result.setMsg("提交反馈");
        result.setData(integer);
        return result;
    }


//    修改密码
    @PostMapping("/editpassword")
    public Result EditPassword(@Param("phone") String phone,@Param("old_password") String old_password,@Param("new_password") String new_password,@Param("new_password1") String new_password1) {
        Result result = new Result();
//        验证旧密码
        Integer integer = myDao.selectOldPassword(phone, old_password);
        if (integer != 0) {
//        成功，修改密码
            Integer integer1 = myDao.updatePassword(phone, new_password);
            if (integer1 == 1) {
//                修改成功
                result.setSuccess(true);
                result.setCode(200);
                result.setMsg("密码修改成功");
                result.setData(integer1);
            }
        }else {
            //        失败，不修改
            result.setSuccess(true);
            result.setCode(200);
            result.setMsg("旧密码错误");
            result.setData(null);
        }
        return result;
    }




//    失物招领
//    获取失物招领列表
    @GetMapping("/lostarticle")
    public Result GetLostArticleList() {
        Result result = new Result();
        List<LostArticle> lostArticleList = myDao.getLostArticleList();
        result.setCode(200);
        result.setSuccess(true);
        result.setMsg("获取失物招领列表");
        result.setData(lostArticleList);
        return result;
    }
//    根据用户id获取失物招领列表
    @PostMapping("/getlostarticlelistbyid")
    public Result GetLostArticleListById(@Param("user_id")Integer user_id) {
        Result result = new Result();
        List<LostArticle> lostArticleListById = myDao.getLostArticleListById(user_id);
        result.setSuccess(true);
        result.setCode(200);
        result.setMsg("获取用户自己发布的失物招领");
        result.setData(lostArticleListById);
        return result;
    }


//    根据用户id删除已发布的失物招领物品
    @PostMapping("/deletelostarticle")
    public Result DeleteLostArticle(@Param("user_id") Integer user_id,@Param("lost_article_id") Integer lost_article_id) {
        Result result = new Result();
//        获取当前失物招领的图片地址
        String lostArticleImg = myDao.getLostArticleImg(lost_article_id);
        int lastSlashIndex = lostArticleImg.lastIndexOf("/");
        String fileName = lostArticleImg.substring(lastSlashIndex + 1);
        System.out.println(lostArticleImg);
        System.out.println(fileName);
//        要删除图片的路径
        ClassLoader classLoader = getClass().getClassLoader();
        String resourcePath = classLoader.getResource("").getPath();
        String filePath = resourcePath + "images/lost_article_img";

        File file = new File(filePath+"\\"+fileName);
        System.out.println(file);
        file.delete();
        //        删除图片
        Integer integer = myDao.deleteLostArticleById(user_id, lost_article_id);
        result.setSuccess(true);
        result.setCode(200);
        result.setMsg("删除成功");
        result.setData(integer);
        return result;
    }



//    发布失物招领
    @PostMapping("/lostarticle")
    public Result LostArticle(
            @Param("user_id") Integer user_id,
            @Param("name") String name,
            @Param("describe") String describe,
            @Param("uploadFile") MultipartFile uploadFile,
            HttpServletRequest request
            ) throws IOException {
        Result result = new Result();
        LostArticle lostArticle = new LostArticle();
        UUID uuid = UUID.randomUUID();
//        获取文件名
        String originalFilename = uploadFile.getOriginalFilename();
        System.out.println(originalFilename);
//        编辑文件名
        String uuidfilename =  uuid + originalFilename;
//        保存文件
        ClassLoader classLoader = getClass().getClassLoader();
        String resourcePath = classLoader.getResource("").getPath();
        String filePath = resourcePath + "images/lost_article_img";
        File file = new File(filePath);
        System.out.println(filePath);
        if (!file.exists()) {
            file.mkdirs();//没有则创建
        }
        //        设置头像文件名（用户id_文件名.jpg/png）
        uploadFile.transferTo(new File(filePath+"\\"+user_id+"_"+uuidfilename));
//        将文件存入数据库
        lostArticle.setLostArticleId(null);
        lostArticle.setUserId(user_id);
        lostArticle.setLostArticleName(name);
        lostArticle.setLostArticleDescribe(describe);
        lostArticle.setLostArticleImg("http://localhost:8080/images/lost_article_img/"+user_id+"_"+uuidfilename);
        lostArticle.setPublishTime(new Date());
        Integer integer = myDao.insertLostArticle(lostArticle);
        result.setSuccess(true);
        result.setCode(200);
        result.setMsg("发布成功");
        result.setData(integer);
        return result;
    }




//    在线相册
    @PostMapping("/album")
    public Result Album(@Param("user_id") Integer user_id) {
        Result result = new Result();
        HashSet set = new HashSet();
        List<Album> albumList = new ArrayList<>();
        System.out.println("user_id"+user_id);
//        根据用户id查询用户已经报名的活动id
        List<Enroll> enrollListByUserId = myDao.getEnrollListByUserId(user_id);
        if (enrollListByUserId.size() != 0) {
//            有相册
            for (int i = 0; i < enrollListByUserId.size(); i ++) {
            Integer timeId = enrollListByUserId.get(i).getTimeId();
            set.add(timeId);
            }
//            根据活动id查询活动相册内容
            List<Integer> list = new ArrayList<>(set);
            for (int i : list) {
                albumList.add(myDao.getAlbumList(i));
            }
            result.setCode(200);
            result.setSuccess(true);
            result.setMsg("在线相册列表");
            result.setData(albumList);
        }else {
//                相册空
            result.setCode(200);
            result.setSuccess(true);
            result.setMsg("在线相册列表");
            result.setData(null);
            }

        return result;
    }


//    获取在线相册中的图片
    @PostMapping("/picture")
    public Result Picture(@Param("album_id")Integer album_id) {
        Result result = new Result();
        List<Picture> pictureList = myDao.getPictureList(album_id);
        System.out.println(album_id);
        System.out.println(pictureList);
        result.setCode(200);
        result.setSuccess(true);
        result.setMsg("获取相册中图片列表");
        result.setData(pictureList);
        return result;
    }


//    合同信息

//    修改用户名
    @PostMapping("/editusername")
    public Result EditUserName(@Param("user_id")Integer user_id, @Param("user_name") String user_name) {
        Result result = new Result();
        Integer integer = myDao.updateUserName(user_id, user_name);
        if (integer == 1) {
            result.setCode(200);
            result.setSuccess(true);
            result.setMsg("修改用户名成功");
            result.setData(integer);
        }
        return result;
    }

//    修改用户头像
    @PostMapping("edituseravatar")
    public Result EditUserAvatar(@Param("user_id") Integer user_id,
                                 @Param("uploadFile") MultipartFile uploadFile
                                 ) throws IOException {
        Result result = new Result();
        UUID uuid = UUID.randomUUID();
//        获取文件名
        String originalFilename = uploadFile.getOriginalFilename();
//        编辑文件名
        String uuidfilename = uuid + originalFilename;
//        保存文件
        ClassLoader classLoader = getClass().getClassLoader();
        String resourcePath = classLoader.getResource("").getPath();
        String filePath = resourcePath + "images/user_avatar";
        File file = new File(filePath);
        System.out.println(filePath);
        if (!file.exists()) {
            file.mkdirs();
        }
//        查询用户当前的头像地址
        String userAvatar = myDao.getUserAvatar(user_id);
        int lastSlashIndex = userAvatar.lastIndexOf("/");
        String fileName = userAvatar.substring(lastSlashIndex + 1);
        if ("github.png".equals(fileName)) {
            //        用户当前的头像地址文件为github.png的话，直接插入
//            设置头像文件名
            uploadFile.transferTo(new File(filePath + "\\" + user_id + "_" + uuidfilename));
//            修改数据库信息
            myDao.updateUserAvatar(user_id,"http://localhost:8080/images/user_avatar/"+user_id +"_"+uuidfilename);
        }else {
            //        用户当前的头像地址文件不为github.png的话，删除原来头像，并插入新头像，修改用户头像地址信息
//            要删除的原头像地址
            File file1 = new File(filePath+"\\"+fileName);
            System.out.println(file1);
            file1.delete();
//            插入新头像
            uploadFile.transferTo(new File(filePath + "\\" + user_id + "_" + uuidfilename));
//            修改用户头像地址信息
            myDao.updateUserAvatar(user_id,"http://localhost:8080/images/user_avatar/"+user_id +"_"+uuidfilename);

        }

        return result;
    }



}
