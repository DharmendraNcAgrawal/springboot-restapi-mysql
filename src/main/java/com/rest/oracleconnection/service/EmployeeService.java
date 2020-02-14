package com.rest.oracleconnection.service;


import com.rest.oracleconnection.entity.Employee;
import com.rest.oracleconnection.exception.EmployeeException;

import java.util.List;

public interface EmployeeService {

    public List<Employee> retrieveEmployees();

    public Employee getEmployee(Long employeeId) throws EmployeeException;

    public void saveEmployee(Employee employee);

    public String deleteEmployee(Long employeeId) throws EmployeeException;

    public void updateEmployee(Employee employee);
}
