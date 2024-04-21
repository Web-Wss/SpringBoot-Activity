package com.ahnu.activity_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class Role {
    private Integer roleId;
    private String roleName;
    private String roleDescribe;
    private Integer powerId;

    private Power power;
}
