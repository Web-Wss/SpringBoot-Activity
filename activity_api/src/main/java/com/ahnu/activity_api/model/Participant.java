package com.ahnu.activity_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class Participant {
    private Integer participantId;
    private String participantName;
    private String participantHeight;
    private String participantWeight;
    private String participantCard;
    private String participantSex;
    private Integer userId;
}
