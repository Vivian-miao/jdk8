package com.joda;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;

import java.util.Date;

/**
 * <h1></h1>
 *
 * @author zhh 2019-05-29
 */
public class JodaTime2 {
    public static void main(String[] args) {
        System.out.println(convertUTC2Date("2019-05-29T17:27:30.123Z"));
        System.out.println("----------");
        // 2019-05-30T01:40:50.751Z 格林威治时间
        System.out.println(convertDate2UTC(new Date()));
        System.out.println("----------");
        System.out.println(convertDate2LocalByDateFormat(new Date(),"yyyy-MM-dd HH:mm:ss"));
        System.out.println("----------");
    }

    private static Date convertUTC2Date(String utcDate) {
        try {
            // 标准UTC时间 2019-05-30T01:40:50.751Z
            DateTime dateTime = DateTime.parse(utcDate, DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ"));
            return dateTime.toDate();
        } catch (Exception e) {
            return null;
        }
    }

    private static String convertDate2UTC(Date javaDate) {
        DateTime dateTime = new DateTime(javaDate, DateTimeZone.UTC);
        return dateTime.toString();
    }

    private static String convertDate2LocalByDateFormat(Date javaDate, String format) {
        DateTime dateTime = new DateTime(javaDate);
        return dateTime.toString(format);
    }
}
