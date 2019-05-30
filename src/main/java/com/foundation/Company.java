package com.foundation;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * <h1>公司类</h1>
 *
 * @author zhh 2019-05-20
 */
@Getter
@Setter
public class Company {
    private String name;
    private List<Employee> employees;
}
