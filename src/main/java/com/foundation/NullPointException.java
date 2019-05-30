package com.foundation;

/**
 * <h1>异常：空指针</h1>
 *
 * @author zhh 2019-05-13
 */
public class NullPointException {
    public static void main(String[] args) {
        // String insuranceName = getInsuranceName(new Person());
        // String name = getInsuranceNameByDeepDoubts(new Person());
        String name = getInsuranceNameByMultExit(new Person());
        System.out.println(name);
    }

    /**
     * 嵌套判断
     */
    private static String getInsuranceNameByDeepDoubts(Person person) {
        if (person != null) {
            Car car = person.getCar();
            if (car != null) {
                Insurance insurance = car.getInsurance();
                if (insurance != null) {
                    return insurance.getName();
                }
            }
        }
        return "unknown";
    }

    /**
     * 返回多种结果
     */
    private static String getInsuranceNameByMultExit(Person person) {
        final String defaultValue = "unknown";
        if (person == null) {
            return defaultValue;
        }
        Car car = person.getCar();
        if (car == null) {
            return defaultValue;
        }
        Insurance insurance = car.getInsurance();
        if (insurance == null) {
            return defaultValue;
        }
        return insurance.getName();
    }

    /**
     * 获取insurance的name
     */
    private static String getInsuranceName(Person person) {
        return person.getCar().getInsurance().getName();
    }
}
