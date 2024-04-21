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
public class SignIn {
    private Integer signInId;
    private Integer enrollId;
    private Integer isSignIn;
    private Date signInTime;

    private Enroll enroll;
    private Activity activity;
    private Time time;
    private Participant participant;

}
