package com.misaka.yuban.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {

    public static final String Time_Format = "yyyy-MM-dd HH:mm:ss";

     public static Date transferStrToTime(String timeStr, String format) {
        try {
            return new SimpleDateFormat(format).parse(timeStr);
        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static String transferTimeToStr(Long timeStamp, String format) {
        return new SimpleDateFormat(format).format(timeStamp);
    }
}
