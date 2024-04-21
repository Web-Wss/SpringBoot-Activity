package com.ahnu.activity_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class Users {
    private Integer userId;
    private String username;
    private String password;
    private String parentName;
    private String participantId;
    private String phone;
    private String userAvatar;
    private Integer roleId;
    private Integer userStatus;
    private String userTime;
}
