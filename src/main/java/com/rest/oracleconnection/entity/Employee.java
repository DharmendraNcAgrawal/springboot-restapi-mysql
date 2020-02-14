package com.rest.oracleconnection.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Dharmendra
 */
@Entity
@Table(name = "EMPLOYEE")
@Getter
@Setter
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "EMPLOYEE_NAME")
    private String name;

    @Column(name = "EMPLOYEE_SALARY")
    private Integer salary;

    @Column(name = "DEPARTMENT")
    private String department;
}
