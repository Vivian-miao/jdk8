package com.foundation;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * <h1>dish 实体类</h1>
 *
 * @author zhh 2019-04-24
 */
@Getter
@Setter
@ToString
public class Dish implements Serializable {

    private final String name;
    private final boolean vegetable;
    private final int calories;
    private final Type type;

    public Dish(String name, boolean vegetable, int calories, Type type) {
        this.name = name;
        this.vegetable = vegetable;
        this.calories = calories;
        this.type = type;
    }

    public enum Type {MEAT, OTHER, FISH}
}
