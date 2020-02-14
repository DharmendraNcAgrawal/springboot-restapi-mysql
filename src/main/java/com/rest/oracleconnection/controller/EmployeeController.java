package com.rest.oracleconnection.controller;

import com.rest.oracleconnection.entity.Employee;
import com.rest.oracleconnection.exception.EmployeeException;
import com.rest.oracleconnection.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping()
    public ResponseEntity<List<Employee>> getEmployees() {
        List<Employee> employees = employeeService.retrieveEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<Employee> getEmployee(@PathVariable(name = "employeeId") Long employeeId) throws EmployeeException {
        Employee employee = employeeService.getEmployee(employeeId);
        return new ResponseEntity(employee, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity saveEmployee(Employee employee) {
        employeeService.saveEmployee(employee);
        System.out.println("Employee Saved Successfully");
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<String> deleteEmployee(@PathVariable(name = "employeeId") Long employeeId) throws EmployeeException {
        String message = employeeService.deleteEmployee(employeeId);
        System.out.println("Employee Deleted Successfully");
        return new ResponseEntity(message, HttpStatus.OK);
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity updateEmployee(@RequestBody Employee employee,
                                         @PathVariable(name = "employeeId") Long employeeId) throws EmployeeException {
        Employee emp = employeeService.getEmployee(employeeId);
        if (emp != null) {
            employeeService.updateEmployee(employee);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

}
