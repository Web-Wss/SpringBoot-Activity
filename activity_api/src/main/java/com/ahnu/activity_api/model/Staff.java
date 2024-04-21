package com.ahnu.activity_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class Staff {
    private Integer staffId;
    private String staffName;
    private String staffAvatar;
    private String staffCard;
    private Integer roleId;
    private String staffPhone;
    private String staffStatus;
    private String staffInfo;
    private String staffTime;
    private String password;
}
