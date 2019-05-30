package com.joda;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Set;
import java.util.TreeSet;

/**
 * <h1></h1>
 *
 * @author zhh 2019-05-30
 */
public class Java8Time {
    public static void main(String[] args) {
        System.out.println("----------");
        localDate();
        System.out.println("----------");
        localDate2();
        System.out.println("----------");
        monthDay();
        System.out.println("----------");
        localTime();
        System.out.println("----------");
        localDatePlus();
        System.out.println("----------");
        localDateMinus();
        System.out.println("----------");
        clockTime();
        System.out.println("----------");
        localDateComparison();
        System.out.println("----------");
        getZone();
        System.out.println("----------");
        getZone();
        System.out.println("----------");
        zoneDateTime();
        System.out.println("----------");
        getYearMonth();
        System.out.println("----------");
        getYearMonth2();
        System.out.println("----------");
        getPeriod();
        System.out.println("----------");
        getInstant();
        System.out.println("----------");
        dateTimeDiff();
        System.out.println("----------");
    }

    /**
     * yyyy-MM-dd
     */
    private static void localDate() {
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate);
        System.out.println(localDate.getYear() + ", " + localDate.getMonthValue() + ", " + localDate.getDayOfMonth());
    }

    private static void localDate2() {
        LocalDate localDate = LocalDate.of(2019, 5, 30);
        System.out.println(localDate);
    }

    private static void monthDay() {
        LocalDate localDate = LocalDate.of(2019, 5, 30);
        MonthDay monthDay = MonthDay.of(localDate.getMonth(), localDate.getDayOfMonth());
        MonthDay monthDay2 = MonthDay.from(LocalDate.of(2018, 5, 29));
        if (monthDay.equals(monthDay2)) {
            System.out.println(monthDay);
        } else {
            System.out.println(monthDay2);
        }
    }

    /**
     * HH:mm:ss.SSS
     * 10:12:41.550210700
     */
    private static void localTime() {
        LocalTime localTime = LocalTime.now();
        System.out.println(localTime);
        LocalTime plusMinutes = localTime.plusHours(1).plusMinutes(7);
        System.out.println(plusMinutes);
    }

    private static void localDatePlus() {
        LocalDate localDate = LocalDate.now();
        LocalDate plus = localDate.plus(2, ChronoUnit.WEEKS);
        System.out.println(plus);
    }

    private static void localDateMinus() {
        LocalDate localDate = LocalDate.now();
        LocalDate minus = localDate.minus(5, ChronoUnit.MONTHS);
        System.out.println(minus);
    }

    private static void clockTime() {
        Clock clock = Clock.systemDefaultZone();
        System.out.println(clock.millis());
    }

    private static void localDateComparison() {
        LocalDate now = LocalDate.now();
        LocalDate date = LocalDate.of(2019, 5, 29);
        System.out.println(now.isBefore(date));
        System.out.println(now.isAfter(date));
        System.out.println(now.isEqual(date));
    }

    private static void getZone() {
        Set<String> zoneIds = ZoneId.getAvailableZoneIds();
        zoneIds.forEach(System.out::println);
        Set<String> treeSet = new TreeSet<>() {
            {
                addAll(zoneIds);
            }
        };
        System.out.println(treeSet);
    }

    private static void zoneDateTime() {
        ZoneId zoneId = ZoneId.of("Asia/Shanghai");
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);
        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, zoneId);
        System.out.println(zonedDateTime);
    }

    private static void getYearMonth() {
        YearMonth yearMonth = YearMonth.now();
        System.out.println(yearMonth);
        System.out.println(yearMonth.isLeapYear());
        System.out.println(yearMonth.lengthOfMonth());
    }

    private static void getYearMonth2() {
        YearMonth yearMonth = YearMonth.of(2020, 2);
        System.out.println(yearMonth);
        System.out.println(yearMonth.isLeapYear());
        System.out.println(yearMonth.lengthOfMonth());
        System.out.println(yearMonth.lengthOfYear());
    }

    private static void getPeriod() {
        LocalDate now = LocalDate.now();
        LocalDate date = LocalDate.of(2020, 5, 25);
        // 包含前面，不包含后面
        Period period = Period.between(now, date);
        System.out.println("years = " + period.getYears());
        System.out.println("months = " + period.getMonths());
        System.out.println("days = " + period.getDays());
    }

    private static void getInstant() {
        Instant instant = Instant.now();
        // UTC时间
        System.out.println(instant);
    }

    private static void dateTimeDiff() {
        LocalDate now = LocalDate.of(2019, 5, 30);
        LocalDate localDate = LocalDate.now();
        LocalDate plus = now.plus(1, ChronoUnit.MONTHS);
        LocalDate minus = now.minus(1, ChronoUnit.MONTHS);
        System.out.println(plus);
        System.out.println(minus);
        if (now.equals(localDate)) {
            System.out.println(localDate);
        }
    }
}
