package com.ahnu.activity_api;

import com.ahnu.activity_api.model.Participant;
import org.apache.commons.lang3.time.DateUtils;
import org.assertj.core.util.DateUtil;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.*;

@SpringBootTest
class ActivityApiApplicationTests {

    @Test
    public static void main(String[] args) {
        String str = "[12,34]";
        str = str.substring(1, str.length() - 1); //去掉字符串开头和结尾的中括号
        String[] strArray = str.split(","); //将字符串按逗号分割成字符串数组
        int[] intArray = new int[strArray.length]; //创建整型数组
        for (int i = 0; i < strArray.length; i++) {
            intArray[i] = Integer.parseInt(strArray[i]); //将字符串转换为整数
        }
        for (int i = 0; i < strArray.length; i ++) {
            System.out.println(strArray[i]);
        }


    }

    @Test
    void contextLoads() {
    }

//    @Test
//    public static void main(String[] args) {
//        List<String> data = getSevenDate();
//        System.out.println(data);
//    }
//
//    public static List<String> getSevenDate() {
//        List<String> dateList = new ArrayList<>();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//
//        for (int i = 0; i < 7; i ++) {
//            Date date = DateUtils.addDays(new Date(),-i);
//            String formDate = sdf.format(date);
//            dateList.add(formDate);
//        }
//        return dateList;
//    }


}
