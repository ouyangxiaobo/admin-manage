package com.springboot.adminmanage.common.utils;

import org.apache.tomcat.jni.Local;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

/**
 * 时间工具类
 */
public class DateUtil {

    public static final String FULL_TIME_PATTERN = "yyyyMMddHHmmss";

    public static final String FULL_TIME_SPLIT_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static String formatFullTime(LocalDateTime localDateTime) {
        return formatFullTime(localDateTime, FULL_TIME_PATTERN);
    }

    public static String formatFullTime(LocalDateTime localDateTime, String pattern) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        return localDateTime.format(dateTimeFormatter);
    }

    private static String getDateFormat(Date date, String dateFormatType) {
        SimpleDateFormat simformat = new SimpleDateFormat(dateFormatType);
        return simformat.format(date);
    }

    public static String formatCSTTime(String date, String format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.UK);
        Date d = sdf.parse(date);
        return DateUtil.getDateFormat(d, format);
    }

    //string 转date
    public static Date strToDateLong(String strDate) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            ParsePosition pos = new ParsePosition(0);
            Date strtodate = formatter.parse(strDate, pos);
            return strtodate;
         }


    //date 转 string
    public static String dateToStrLong(Date dateDate) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateString = formatter.format(dateDate);
            return dateString;
         }

    public static void main(String[] args) {
        try {
            String date=DateUtil.formatCSTTime("Sat Feb 01 08:00:00 SGT 2020","yyyy-MM-dd HH:mm:ss");
            System.out.println(date);
             Date dates=DateUtil.strToDateLong("2019-06-16 08:00:00");
            System.out.println(dates);


        }catch (Exception e){
            e.printStackTrace();
        }



    }

}
