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
public class Agreement {
    private Integer agreementId;
    private String agreementName;
    private String agreementContent;
    private String createTime;
}
