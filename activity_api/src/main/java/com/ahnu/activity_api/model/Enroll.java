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
public class Enroll {
    private Integer enrollId;
    private Integer userId;
    private Integer activityId;
    private Integer timeId;
    private Integer participantId;
    private Integer staffId;
    private Integer contractId;
    private String userRemarks;
    private Date publishTime;

    private Participant participant;
    private Time time;
    private Activity activity;
    private List<Header> headerList;
    private Users users;
    private Staff staff;
}
