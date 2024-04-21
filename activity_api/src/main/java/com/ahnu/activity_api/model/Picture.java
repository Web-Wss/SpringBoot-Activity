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
public class Picture {
    private Integer pictureId;
    private Integer albumId;
    private String pictureImg;
    private Integer staffId;
    private Date publishTime;
}
