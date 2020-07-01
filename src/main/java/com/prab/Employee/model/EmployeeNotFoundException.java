package com.prab.Employee.model;

public class EmployeeNotFoundException extends RuntimeException {

    EmployeeNotFoundException(Long id) {
        super("Could Not find Employee " + id);
    }
}
