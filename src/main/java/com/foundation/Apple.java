package com.foundation;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <h1>apple 实体类</h1>
 *
 * @author zhh 2019-04-23
 */
@Getter
@Setter
@ToString
public class Apple {
    private String color;
    private long weight;

    public Apple(String color, long weight) {
        this.color = color;
        this.weight = weight;
    }
}
