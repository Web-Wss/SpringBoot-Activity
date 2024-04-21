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
public class Album {
    private Integer albumId;
    private Integer timeId;
    private String activityName;
    private String albumHeaderPath;
    private Integer albumStatus;
    private Integer staffId;
    private Date createTime;

    private Time time;

}
