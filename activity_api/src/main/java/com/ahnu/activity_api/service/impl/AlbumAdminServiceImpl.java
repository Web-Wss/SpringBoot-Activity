package com.ahnu.activity_api.service.impl;

import com.ahnu.activity_api.dao.AlbumAdminDao;
import com.ahnu.activity_api.model.Album;
import com.ahnu.activity_api.model.Picture;
import com.ahnu.activity_api.model.Time;
import com.ahnu.activity_api.service.AlbumAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumAdminServiceImpl implements AlbumAdminService {

    @Autowired
    private AlbumAdminDao albumAdminDao;

    @Override
    public List<Album> GetAlbumList() {
        return albumAdminDao.GetAlbumList();
    }

    @Override
    public List<Picture> GetPictureByAlbumId(Integer album_id) {
        return albumAdminDao.GetPictureByAlbumId(album_id);
    }

    @Override
    public List<Time> GetTimeList() {
        return albumAdminDao.GetTimeList();
    }

    @Override
    public List<Album> GetTimeListAndAlbum() {
        return albumAdminDao.GetTimeListAndAlbum();
    }

    @Override
    public Time GetInfoByTImeId(Integer time_id) {
        return albumAdminDao.GetInfoByTImeId(time_id);
    }

    @Override
    public Integer CreateAlbum(Album album) {
        return albumAdminDao.CreateAlbum(album);
    }

    @Override
    public Integer UploadAlbumPicture(Picture picture) {
        return albumAdminDao.UploadAlbumPicture(picture);
    }

    @Override
    public Integer DeletePictureById(Integer picture_id) {
        return albumAdminDao.DeletePictureById(picture_id);
    }

    @Override
    public Integer DeleteAlbumById(Integer album_id) {
        return albumAdminDao.DeleteAlbumById(album_id);
    }

    @Override
    public List<Picture> GetPictureListById(Integer album_id) {
        return albumAdminDao.GetPictureListById(album_id);
    }

    @Override
    public Integer DeletePictureByAlbumId(Integer album_id) {
        return albumAdminDao.DeletePictureByAlbumId(album_id);
    }
}
