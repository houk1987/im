package com.pubTools.toos;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * Created by a on 2014/7/23.
 */
public class DateTools {

    private DateTools() {
    }

    public static Timestamp conventToTimeStamp(String date){
        if(date == null) return null;
        return  Timestamp.valueOf(date);
    }

    public static String timeStampConventToString(Timestamp timestamp){
        if(timestamp == null) return null;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�����ʽ������ʾ����
        Timestamp now = new Timestamp(System.currentTimeMillis());//��ȡϵͳ��ǰʱ��
        String str = df.format(now);
        return str;
    }
}
