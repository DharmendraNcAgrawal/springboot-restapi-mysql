package com.rest.oracleconnection.service.impl;

import com.rest.oracleconnection.entity.Employee;
import com.rest.oracleconnection.exception.EmployeeException;
import com.rest.oracleconnection.exception.ErrorCodes;
import com.rest.oracleconnection.repository.EmployeeRepository;
import com.rest.oracleconnection.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> retrieveEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees;
    }

    public Employee getEmployee(Long employeeId) throws EmployeeException {
//        Optional<Employee> optEmp = employeeRepository.findById(employeeId);
//        return optEmp.get();
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeException(ErrorCodes.RESOURCE_NOT_FOUND));
        return employee;
    }

    public void saveEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    public String deleteEmployee(Long employeeId) throws EmployeeException {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeException(ErrorCodes.RESOURCE_NOT_FOUND));
        employeeRepository.deleteById(employeeId);
        String message = "Employee Deleted Successfully";
        return  message;
    }

    public void updateEmployee(Employee employee) {
        employeeRepository.save(employee);
    }
}
