package com.foundation;

import java.util.Optional;

/**
 * <h1>Optional example</h1>
 *
 * @author zhh 2019-05-13
 */
public class OptionalInAction {
    public static void main(String[] args) {
        Optional.ofNullable(getInsuranceNameByOptional(null)).ifPresent(System.out::println);
    }

    private static String getInsuranceNameByOptional(OptionalPerson person) {
        return Optional.ofNullable(person)
                .flatMap(OptionalPerson::getCar)
                .flatMap(OptionalCar::getInsurance)
                .map(Insurance::getName)
                .orElse("unknown");
    }
}
