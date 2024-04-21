package com.ahnu.activity_api.controller;

import com.ahnu.activity_api.dao.AlbumAdminDao;
import com.ahnu.activity_api.model.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/albumadmin")
public class AlbumAdminController {

    @Autowired
    private AlbumAdminDao albumAdminDao;

//    获取相册列表
    @GetMapping("/getalbumlist")
    public Result GetAlbumList(){
        Result result = new Result();
        List<Album> albums = albumAdminDao.GetAlbumList();
        result.setSuccess(true);
        result.setCode(200);
        result.setMsg("获取相册列表");
        result.setData(albums);
        return result;
    }


//    获取图片列表通过相册id
    @PostMapping("/getpicturebyalbumid")
    public Result GetPictureByAlbumId(@Param("album_id")Integer album_id){
        Result result = new Result();
        List<Picture> pictures = albumAdminDao.GetPictureByAlbumId(album_id);
        result.setCode(200);
        result.setSuccess(true);
        result.setMsg("获取图片列表");
        result.setData(pictures);
        return result;
    }


//    创建相册方法，获取未被创建的时间id
    @GetMapping("/gettimeidlist")
    public Result GetTimeIdList() {
        Result result = new Result();
//        获取时间表中的所有时间id
        List<Time> timeList = albumAdminDao.GetTimeList();
        System.out.println(timeList);
        int[] timeId = new int[timeList.size()];
        for (int i = 0; i < timeList.size(); i ++) {
            timeId[i] = timeList.get(i).getTimeId();
        }
        for (int i = 0; i < timeId.length; i ++) {
            System.out.println(timeId[i]);
        }
//        获取相册表中的时间id
        List<Album> albums = albumAdminDao.GetTimeListAndAlbum();
        int[] timeIdAlbum = new int[albums.size()];
        for (int i = 0; i < albums.size(); i ++) {
            timeIdAlbum[i] = albums.get(i).getTimeId();
        }
        for (int i = 0; i < timeIdAlbum.length; i ++) {
            System.out.println(timeIdAlbum[i]);
        }
//        找出未被使用的时间id
        int[] differentElements = IntStream.of(timeId)
                .filter(i -> !IntStream.of(timeIdAlbum).anyMatch(j -> j == i))
                .toArray();

        System.out.println("Different elements: " + Arrays.toString(differentElements));
        result.setCode(200);
        result.setSuccess(true);
        result.setMsg("获取未被使用的时间id");
        result.setData(differentElements);
        return result;
    }

//    根据时间id获取创建相册所需信息（活动id、活动时间、活动名称）
    @PostMapping("/getinfobytimeid")
    public Result GetInfoByTImeId(@Param("time_id")Integer time_id) {
        Result result = new Result();
        System.out.println(time_id);
        Time time = albumAdminDao.GetInfoByTImeId(time_id);
        result.setSuccess(true);
        result.setCode(200);
        result.setMsg("获取相关信息根据时间id");
        result.setData(time);
        return result;
    }

//    创建相册
    @PostMapping("/createalbum")
    public Result CreateAlbum(@Param("activity_id")Integer activity_id,
                              @Param("staff_id")Integer staff_id,
                              @Param("activity_name")String activity_name,
                              @Param("time_id")Integer time_id,
                              @Param("time_name")String time_name,
                              @Param("uploadFile") MultipartFile uploadFile) throws IOException {
        Result result = new Result();
        System.out.println(activity_id);
        System.out.println(staff_id);
        System.out.println(activity_name);
        System.out.println(time_id);
        System.out.println(time_name);
        System.out.println(uploadFile);
        Album album = new Album();
        UUID uuid = UUID.randomUUID();
//        获取文件名
        String originalFileName = uploadFile.getOriginalFilename();
        System.out.println(originalFileName);
//        编辑文件名
        String uuidfilename = uuid + originalFileName;
//        保存文件
        ClassLoader classLoader = getClass().getClassLoader();
        String resourcePath = classLoader.getResource("").getPath();
        String filePath = resourcePath + "images/album_header";
        File file = new File(filePath);
        System.out.println(filePath);
        if (!file.exists()) {
            file.mkdirs();
        }
//        设置相册头文件 文件名（职工id+）
        uploadFile.transferTo(new File(filePath + "\\" + staff_id + "_" + uuidfilename));
//        蒋文件存入数据库
        album.setTimeId(time_id);
        album.setActivityName(activity_name);
        album.setAlbumHeaderPath("http://localhost:8080/images/album_header/"+staff_id+"_"+uuidfilename);
        album.setAlbumStatus(1);
        album.setStaffId(staff_id);
//        执行
        Integer integer = albumAdminDao.CreateAlbum(album);
        result.setCode(200);
        result.setSuccess(true);
        result.setMsg("相册创建成功");
        result.setData(integer);
        return result;
    }

//    上传相册中图片
    @PostMapping("/uploadalbumpicture")
    public Result UploadAlbumPicture(@Param("album_id")Integer album_id,
                                     @Param("staff_id")Integer staff_id,
                                     @Param("uploadFile") MultipartFile uploadFile
                                     ) throws IOException {
        Result result = new Result();
        System.out.println(album_id);
        System.out.println(staff_id);
        System.out.println(uploadFile);
        Picture picture = new Picture();
        UUID uuid = UUID.randomUUID();
//        获取文件名
        String originalFileName = uploadFile.getOriginalFilename();
        System.out.println(originalFileName);
//        编辑文件名
        String uuidfilename = uuid + originalFileName;
//        保存文件
        ClassLoader classLoader = getClass().getClassLoader();
        String resourcePath = classLoader.getResource("").getPath();
        String filePath = resourcePath + "images/picture_img";
        File file = new File(filePath);
        if (!file.exists()) {
            file.exists();
        }
//        设置图片名
        uploadFile.transferTo(new File(filePath + "\\" + staff_id + "_" + uuidfilename));

//        存入数据库
        picture.setStaffId(staff_id);
        picture.setAlbumId(album_id);
        picture.setPictureImg("http://localhost:8080/images/picture_img/"+staff_id+"_"+uuidfilename);
//        执行
        Integer integer = albumAdminDao.UploadAlbumPicture(picture);
        result.setSuccess(true);
        result.setCode(200);
        result.setMsg("图片上传成功");
        result.setData(integer);
        return result;
    }

//    删除某个图片
    @PostMapping("/deletepicturebyid")
    public Result DeletePictureById(@Param("picture_id")Integer picture_id,
                                    @Param("picture_img")String picture_img) {
        Result result = new Result();
        System.out.println(picture_id);
        System.out.println(picture_img);
        String pictureImg = picture_img.substring(picture_img.lastIndexOf("/")+1);
        System.out.println(pictureImg);
//        删除文件
        ClassLoader classLoader = getClass().getClassLoader();
        String resourcePath = classLoader.getResource("").getPath();
        String filePath = resourcePath + "images/picture_img";
        File file = new File(filePath + "\\" + pictureImg);
        file.delete();
//        删除数据库
        Integer integer = albumAdminDao.DeletePictureById(picture_id);
        result.setCode(200);
        result.setSuccess(true);
        result.setMsg("删除成功");
        result.setData(integer);
        return result;
    }

//    删除相册
    @PostMapping("/deletealbumbyid")
    public Result DeleteAlbumById(@Param("album_id")Integer album_id,
                                  @Param("album_header_img")String album_header_img) {
        Result result = new Result();
        System.out.println(album_id);
//        删除相册头图
        String albumHeaderImg = album_header_img.substring(album_header_img.lastIndexOf("/")+1);
//        删除文件
        ClassLoader classLoader = getClass().getClassLoader();
        String resourcePath = classLoader.getResource("").getPath();
        String filePath = resourcePath + "images/album_header";
        File file = new File(filePath + "\\" + albumHeaderImg);
        file.delete();
//        删除数据库记录根据album_id
        Integer integer = albumAdminDao.DeleteAlbumById(album_id);
//        获取图片表中图片根据相册id
        List<Picture> pictures = albumAdminDao.GetPictureListById(album_id);
        String[] pictureList = new String[pictures.size()];
        for (int i = 0; i < pictures.size(); i ++) {
            pictureList[i] = pictures.get(i).getPictureImg().substring(pictures.get(i).getPictureImg().lastIndexOf("/")+1);
        }
//        执行删除图片文件
        String filePath1 = resourcePath + "images/picture_img";
        for (int i = 0; i < pictureList.length; i ++) {
            System.out.println(pictureList[i]);
            File file1 = new File(filePath1 + "\\" + pictureList[i]);
            file1.delete();
        }
//        删除数据库记录
        Integer integer1 = albumAdminDao.DeletePictureByAlbumId(album_id);
        result.setSuccess(true);
        result.setCode(200);
        result.setMsg("删除相册成功");
        result.setData(integer+integer1);
        return result;
    }


}
