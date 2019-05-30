package com.foundation;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * <h1>公司与员工的测试</h1>
 *
 * @author zhh 2019-05-20
 */
public class OptionalAction {
    public static void main(String[] args) {
        Employee employee1 = new Employee();
        employee1.setName("jack");

        Employee employee2 = new Employee();
        employee2.setName("mike");

        Company company = new Company();
        company.setName("company");

        List<Employee> employees = Arrays.asList(employee1, employee2);
        // company.setEmployees(employees);

        Optional.of(Optional.of(company)
                .map(Company::getEmployees)
                .orElse(Collections.emptyList()))
                .ifPresent(System.out::println);
    }
}
