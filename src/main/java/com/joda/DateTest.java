package com.joda;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.Date;

/**
 * <h1></h1>
 *
 * @author zhh 2019-05-15
 */
public class DateTest {
    public static void main(String[] args) {
        Date date = new Date(119, 1, 104);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        /*for (int i = 0; i < 30; i++) {
            new Thread(()->{
                for (int j = 0; j < 100; j++) {
                    Date dateFormat = null;
                    try {
                        dateFormat = sdf.parse("20190518");
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    System.out.println(dateFormat);
                }
            }).start();
        }*/

        localDate();
        localTime();
        localDateAndTime();
        instant();
        duration();
        period();
        dateFormat();
    }

    private static void localDate() {
        LocalDate localDate = LocalDate.of(2019, 5, 15);
        System.out.println("year = " + localDate.getYear());
        System.out.println("month = " + localDate.getMonth());
        System.out.println("monthValue = " + localDate.getMonthValue());
        System.out.println("dayOfYear = " + localDate.getDayOfYear());
        System.out.println("dayOfMonth = " + localDate.getDayOfMonth());
        System.out.println("dayOfWeek = " + localDate.getDayOfWeek());
        System.out.println(localDate.get(ChronoField.ERA));
        System.out.println(localDate.toString());
    }

    private static void localTime() {
        LocalTime now = LocalTime.now();
        System.out.println("hour = " + now.getHour());
        System.out.println("minute = " + now.getMinute());
        System.out.println("second = " + now.getSecond());
        System.out.println(now.toString());
    }

    private static void localDateAndTime() {
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        LocalDateTime dateTime = LocalDateTime.of(date, time);
        System.out.println(dateTime.toString());
    }

    private static void instant() {
        Instant start = Instant.now();
        try {
            Thread.sleep(1000L);
            Instant end = Instant.now();
            Duration duration = Duration.between(start, end);
            System.out.println(duration.toMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void duration() {
        LocalTime now = LocalTime.now();
        LocalTime time = now.minusHours(1);
        Duration duration = Duration.between(now, time);
        System.out.println(duration.toHours());
    }

    private static void period() {
        Period period = Period.between(LocalDate.of(2014, 1, 10), LocalDate.of(2019, 5, 16));
        System.out.println("months = " + period.getMonths());
        System.out.println("years = " + period.getYears());
        System.out.println("days = " + period.getDays());
    }

    private static void dateFormat() {
        LocalDate date = LocalDate.now();
        String format1 = date.format(DateTimeFormatter.ISO_LOCAL_DATE);
        String format2 = date.format(DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println(format1);
        System.out.println(format2);
    }
}
