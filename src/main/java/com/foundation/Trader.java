package com.foundation;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <h1>交易实体类</h1>
 *
 * @author zhh 2019-04-29
 */
@Getter
@Setter
@ToString
public class Trader {

    private final String name;
    private final String city;

    public Trader(String name, String city) {
        this.name = name;
        this.city = city;
    }
}
