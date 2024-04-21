package com.ahnu.activity_api.dao;

import com.ahnu.activity_api.model.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface MyDao {

    //    获取活动列表
    List<Enroll> getEnrollList(@Param("user_id") Integer user_id);

//    提交签名
    Integer UploadResultImg(Contract contract);
//    修改报名表中的合同字段
    Integer updateEnrollInfoAndContractId(Enroll enroll);

//    查看电子签名
    Contract SearchContractIngoImg(@Param("contract_id")Integer contract_id);


//    根据协议id获取协议内容
    Agreement GetAgreementContentById(@Param("agreement_id") Integer agreement_id);

//    获取我的收藏列表
    List<CollectionTab> getCollectionList(@Param("user_id") Integer user_id);

//    删除收藏
    Integer DeleteCollection(@Param("collection_id")Integer collection_id);

//    根据用户id获取参与者列表
    String getParticipantId(@Param("user_id") Integer user_id);
    Participant getParticipantList(@Param("participant_id") Integer participant_id);
//    删除参与者
    Integer deleteParticipant(@Param("user_id") Integer user_id,@Param("participant_id") String participant_id);
    Integer deleteParticipantByPId(@Param("participantId") Integer participantId);

//    添加参与者
    Integer insertParticipant(Participant participant);
    Integer updateParticipant(@Param("user_id") Integer user_id,@Param("participant_id") String participant_id);



//    提交意见
    Integer insertOption(Option option);

//    验证旧密码
    Integer selectOldPassword(@Param("phone") String phone,@Param("old_password") String old_password);
//     修改密码
    Integer updatePassword(@Param("phone") String phone,@Param("new_password") String new_password);

//失物招领列表
    List<LostArticle> getLostArticleList();
//    根据用户id获取失物招领列表
    List<LostArticle> getLostArticleListById(@Param("user_id")Integer user_id);
//    根据用户id删除已发布的失物招领物品
    String getLostArticleImg(@Param("lost_article_id") Integer lost_article_id);
    Integer deleteLostArticleById(@Param("user_id") Integer user_id,@Param("lost_article_id") Integer lost_article_id);

//    在线相册
//    根据用户id查询用户已经报名的id
    List<Enroll> getEnrollListByUserId(@Param("user_id") Integer user_id);
    Album getAlbumList(@Param("time_id")Integer time_id);

//获取在线相册中的图片
    List<Picture> getPictureList(@Param("album_id")Integer album_id);


//    失物招领发布
    Integer insertLostArticle(LostArticle lostArticle);


//    修改用户名
    Integer updateUserName(@Param("user_id") Integer user_id,@Param("user_name")String user_name);

//    修改用户头像
//    查询用户当前的头像地址
    String getUserAvatar(@Param("user_id") Integer user_id);
//    修改数据库信息--用户头像地址信息
    Integer updateUserAvatar(@Param("user_id")Integer user_id,@Param("user_avatar")String user_avatar);



}
