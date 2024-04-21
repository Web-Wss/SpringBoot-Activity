package com.ahnu.activity_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class Contract {

    private Integer contractId;
    private Integer enrollId;
    private Integer agreementId;
    private String autographImg;
    private String createTime;

}
