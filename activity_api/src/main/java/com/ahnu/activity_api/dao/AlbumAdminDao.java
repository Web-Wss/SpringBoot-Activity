package com.ahnu.activity_api.dao;

import com.ahnu.activity_api.model.Album;
import com.ahnu.activity_api.model.Picture;
import com.ahnu.activity_api.model.Time;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AlbumAdminDao {

//    获取相册列表
    List<Album> GetAlbumList();

//    获取图片列表通过相册id
    List<Picture> GetPictureByAlbumId(@Param("album_id")Integer album_id);

//    获取未被创建的时间id
    List<Time> GetTimeList();
//    获取相册表中时间id
    List<Album> GetTimeListAndAlbum();

    //    根据时间id获取创建相册所需信息
    Time GetInfoByTImeId(@Param("time_id")Integer time_id);

    //    创建相册
    Integer CreateAlbum(Album album);

    //    上传相册中图片
    Integer UploadAlbumPicture(Picture picture);

//    删除某个图片
    Integer DeletePictureById(Integer picture_id);

//    删除相册
    //        删除数据库记录根据album_id
    Integer DeleteAlbumById(@Param("album_id")Integer album_id);
//        获取图片表中图片根据相册id
    List<Picture> GetPictureListById(@Param("album_id")Integer album_id);
    Integer DeletePictureByAlbumId(@Param("album_id")Integer album_id);

}
