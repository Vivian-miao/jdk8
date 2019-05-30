package com.foundation;

import java.util.Optional;

/**
 * <h1>Optional 的用法</h1>
 *
 * @author zhh 2019-05-13
 */
public class OptionalUsage {
    public static void main(String[] args) {
        // Optional.empty().get();

        // Optional.of(new Insurance()).get();

        /*Optional.ofNullable(null).orElseGet(Insurance::new);
        Optional.ofNullable(null).orElse(new Insurance());
        Optional.ofNullable(null).orElseThrow(RuntimeException::new);
        Optional.ofNullable(null).orElseThrow(() -> new RuntimeException("not have reference"));*/

        Optional<Insurance> insurance = Optional.of(new Insurance());
        // Insurance insurance2 = insurance.filter(insurance1 -> insurance1.getName() != null).get();
        // System.out.println(insurance2);

        System.out.println(insurance.map(Insurance::getName).orElse("empty value"));

        System.out.println(insurance.map(Insurance::getName).isPresent());

        getInsuranceName(null);

        getInsNameByOptional(null);
    }

    private static String getInsuranceName(Insurance insurance) {
        if (insurance == null) {
            return "unknown";
        }
        return insurance.getName();
    }

    private static String getInsNameByOptional(Insurance insurance) {
        return Optional.ofNullable(insurance).map(Insurance::getName).orElse("unknown");
    }
}
