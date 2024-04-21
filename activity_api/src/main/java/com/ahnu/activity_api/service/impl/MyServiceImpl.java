package com.ahnu.activity_api.service.impl;

import com.ahnu.activity_api.dao.MyDao;
import com.ahnu.activity_api.model.*;
import com.ahnu.activity_api.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyServiceImpl implements MyService {

    @Autowired
    private MyDao myDao;

    @Override
    public List<Enroll> getEnrollList(Integer user_id) {
        return myDao.getEnrollList(user_id);
    }

    @Override
    public Integer UploadResultImg(Contract contract) {
        return myDao.UploadResultImg(contract);
    }

    @Override
    public Integer updateEnrollInfoAndContractId(Enroll enroll) {
        return myDao.updateEnrollInfoAndContractId(enroll);
    }

    @Override
    public Contract SearchContractIngoImg(Integer contract_id) {
        return myDao.SearchContractIngoImg(contract_id);
    }


    @Override
    public Agreement GetAgreementContentById(Integer agreement_id) {
        return myDao.GetAgreementContentById(agreement_id);
    }

    @Override
    public List<CollectionTab> getCollectionList(Integer user_id) {
        return myDao.getCollectionList(user_id);
    }

    @Override
    public Integer DeleteCollection(Integer collection_id) {
        return myDao.DeleteCollection(collection_id);
    }

    @Override
    public String getParticipantId(Integer user_id) {
        return myDao.getParticipantId(user_id);
    }

    @Override
    public Participant getParticipantList(Integer participant_id) {
        return myDao.getParticipantList(participant_id);
    }

    @Override
    public Integer deleteParticipant(Integer user_id, String participant_id) {
        return myDao.deleteParticipant(user_id, participant_id);
    }

    @Override
    public Integer deleteParticipantByPId(Integer participantId) {
        return myDao.deleteParticipantByPId(participantId);
    }

    @Override
    public Integer insertParticipant(Participant participant) {
        return myDao.insertParticipant(participant);
    }

    @Override
    public Integer updateParticipant(Integer user_id, String participant_id) {
        return myDao.updateParticipant(user_id,participant_id);
    }

    @Override
    public Integer insertOption(Option option) {
        return myDao.insertOption(option);
    }

    @Override
    public Integer selectOldPassword(String phone, String old_password) {
        return myDao.selectOldPassword(phone, old_password);
    }

    @Override
    public Integer updatePassword(String phone, String new_password) {
        return myDao.updatePassword(phone, new_password);
    }

    @Override
    public List<LostArticle> getLostArticleList() {
        return myDao.getLostArticleList();
    }

    @Override
    public List<LostArticle> getLostArticleListById(Integer user_id) {
        return myDao.getLostArticleListById(user_id);
    }

    @Override
    public String getLostArticleImg(Integer lost_article_id) {
        return myDao.getLostArticleImg(lost_article_id);
    }

    @Override
    public Integer deleteLostArticleById(Integer user_id, Integer lost_article_id) {
        return myDao.deleteLostArticleById(user_id, lost_article_id);
    }

    @Override
    public List<Enroll> getEnrollListByUserId(Integer user_id) {
        return myDao.getEnrollListByUserId(user_id);
    }

    @Override
    public Album getAlbumList(Integer activity_id) {
        return myDao.getAlbumList(activity_id);
    }

    @Override
    public List<Picture> getPictureList(Integer album_id) {
        return myDao.getPictureList(album_id);
    }

    @Override
    public Integer insertLostArticle(LostArticle lostArticle) {
        return myDao.insertLostArticle(lostArticle);
    }

    @Override
    public Integer updateUserName(Integer user_id, String user_name) {
        return myDao.updateUserName(user_id, user_name);
    }

    @Override
    public String getUserAvatar(Integer user_id) {
        return myDao.getUserAvatar(user_id);
    }

    @Override
    public Integer updateUserAvatar(Integer user_id, String user_avatar) {
        return myDao.updateUserAvatar(user_id, user_avatar);
    }


}
