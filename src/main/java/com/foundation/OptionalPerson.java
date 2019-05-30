package com.foundation;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Optional;

/**
 * <h1>Person类</h1>
 *
 * @author zhh 2019-05-13
 */
@Getter
@Setter
@ToString
class OptionalPerson {
    private Optional<OptionalCar> car;
}
