package com.ahnu.activity_api.utils;

import org.apache.commons.lang3.time.DateUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GetSevenDate {
    public static List<String> getSevenDate() {
        List<String> dateList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        for (int i = 0; i < 7; i ++) {
            Date date = DateUtils.addDays(new Date(),-i);
            String formDate = sdf.format(date);
            dateList.add(formDate);
        }
        return dateList;
    }
}
