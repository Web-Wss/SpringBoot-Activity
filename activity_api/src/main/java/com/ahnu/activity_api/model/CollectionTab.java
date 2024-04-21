package com.ahnu.activity_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class CollectionTab {
    private Integer collectionId;
    private Integer activityId;
    private Integer userId;
    private Date createTime;

    private Activity activity;
    private List<Header> headerList;
}
