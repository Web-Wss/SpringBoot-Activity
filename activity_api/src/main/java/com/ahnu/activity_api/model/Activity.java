package com.ahnu.activity_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class Activity {

    private Integer activityId;
    private Integer classificationId;
    private String activityName;
    private String timeId;
    private String activityStaffId;
    private String activityDescribe;
    private Integer browseSum;
    private String activityAge;
    private Integer activityStatus;
    private Integer agreementId;
    private Integer staffId;
    private String publishTime;
//    private Integer personSum;
    private String activityPrice;
//    private Integer isSignIn;

    private List<Header> headerList;
    private List<Img> imgList;
    private Classification classification;
    private List<Time> timeList;

}
