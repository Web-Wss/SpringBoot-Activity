package com.ahnu.activity_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class LostArticle {
    private Integer lostArticleId;
    private Integer userId;
    private String lostArticleName;
    private String lostArticleDescribe;
    private String lostArticleImg;
    private Date publishTime;

    private Users users;
}
