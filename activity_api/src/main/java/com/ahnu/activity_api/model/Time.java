package com.ahnu.activity_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class Time {
    private Integer timeId;
    private String timeName;
    private Integer activityId;
    private String timeContent;
    private Integer personSum;
    private Integer isSignIn;

//    已报名人数
    private Integer EnrollSum;
    private Activity activity;

}
