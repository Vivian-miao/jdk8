package com.foundation;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <h1>Transaction</h1>
 *
 * @author zhh 2019-04-29
 */
@Getter
@Setter
@ToString
public class Transaction {
    private final Trader trader;
    private final int year;
    private final int value;

    public Transaction(Trader trader, int year, int value) {
        this.trader = trader;
        this.year = year;
        this.value = value;
    }
}
