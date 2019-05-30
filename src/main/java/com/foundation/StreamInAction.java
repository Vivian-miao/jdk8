package com.foundation;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * <h1>Stream 综合</h1>
 *
 * @author zhh 2019-04-29
 */
public class StreamInAction {
    public static void main(String[] args) {

        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950),
                new Transaction(raoul, 2011, 400),
                new Transaction(raoul, 2012, 1000)
        );

        // year=2011年的，并对value进行排序
        List<Transaction> collect = transactions.stream().filter(transaction -> transaction.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(Collectors.toList());

        System.out.println(collect);

        System.out.println("------------");

        // Trader里city的唯一值
        transactions.stream().map(transaction -> transaction.getTrader().getCity())
                .distinct().forEach(System.out::println);

        System.out.println("------------");

        // city=Cambridge的，并对name进行排序
        transactions.stream().map(Transaction::getTrader)
                .filter(trader -> "Cambridge".equals(trader.getCity()))
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .forEach(System.out::println);

        System.out.println("------------");

        // 根据Trader的name的字母个数,顺序进行排序，并返回String
        String reduce = transactions.stream().map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted()
                .reduce("", (name1, name2) -> name1 +" "+ name2);

        System.out.println(reduce);

        System.out.println("------------");

        // city=Milan的Trader
        boolean anyMatch = transactions.stream().anyMatch(transaction -> "Milan".equals(transaction.getTrader().getCity()));

        boolean match = transactions.stream().map(Transaction::getTrader).anyMatch(trader -> "Milan".equals(trader.getCity()));

        System.out.println(anyMatch + "---" + match);

        System.out.println("------------");

        // 打印出所有的city=Cambridge的value
        transactions.stream().filter(transaction -> "Cambridge".equals(transaction.getTrader().getCity()))
                .map(Transaction::getValue)
                .forEach(System.out::println);

        System.out.println("------------");

        // 获取value的最大值
        Optional<Integer> maxValue = transactions.stream().map(Transaction::getValue).reduce(Integer::max);

        Optional<Integer> max = transactions.stream().map(Transaction::getValue).reduce((i, j) -> i > j ? i : j);
        System.out.println(max.get());

        System.out.println(maxValue);

        System.out.println("------------");

        // 获取value的最小值
        Optional<Integer> minValue = transactions.stream().map(Transaction::getValue).reduce(Integer::min);

        System.out.println(minValue.get());

    }
}
