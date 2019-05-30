package com.joda;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;

/**
 * <h1></h1>
 *
 * @author zhh 2019-05-29
 */
public class JodaTime {
    public static void main(String[] args) {
        DateTime today = new DateTime();
        DateTime tomorrow = today.plusDays(1);
        DateTime yesterday = today.minusDays(1);
        System.out.println("----------");
        System.out.println(today.toString("yyyy-MM-dd"));
        System.out.println(tomorrow.toString("yyyy-MM-dd"));
        System.out.println(yesterday.toString("yyyy-MM-dd"));

        System.out.println("----------");

        DateTime dayOfMonth = today.withDayOfMonth(1);
        System.out.println(dayOfMonth.toString("yyyy-MM-dd"));

        System.out.println("----------");

        LocalDate localDate = new LocalDate();
        System.out.println(localDate);

        System.out.println("----------");
        // 当前日期加上3个月的那个月的最大日期
        localDate = localDate.plusMonths(3).dayOfMonth().withMaximumValue();
        System.out.println(localDate);

        System.out.println("----------");
        // 计算2年前的第3个月的最后一天的日期
        DateTime date = new DateTime();
        DateTime dateTime = date.minusYears(2).monthOfYear().setCopy(3).dayOfMonth().withMaximumValue();
        System.out.println(dateTime.toString("yyyy-MM-dd"));
        System.out.println("----------");

        DateTime time = new DateTime("2019-01-01");
        DateTime minusDays = time.minusDays(30);
        System.out.println(minusDays.toString("yyyy-MM-dd HH:mm:ss"));
        System.out.println("----------");
    }
}
