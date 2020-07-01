package com.prab.Employee.model;


import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/employees")
    List<Employee> all()
    {
        return employeeRepository.findAll();
    }

    @GetMapping("/employees/{id}")
    Employee one(@PathVariable Long id)
    {
        return employeeRepository.findById(id)
                .orElseThrow(()->new EmployeeNotFoundException(id));
    }

    @PostMapping("/employees")
    Employee addNewEmployee(@RequestBody Employee newEmployee)
    {
        return employeeRepository.save(newEmployee);
    }

    @PutMapping("/employees/{id}")
    Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id)
    {
        return employeeRepository.findById(id)
                .map(employee -> {
                    employee.setName(newEmployee.getName());
                    employee.setRole(newEmployee.getRole());
                    return employeeRepository.save(employee);
                })
                .orElseGet(()->{
                    newEmployee.setId(id);
                    return employeeRepository.save(newEmployee);

                });
    }

    @DeleteMapping("/employees/{id}")
    void deleteEmployee(@PathVariable Long id)
    {
        employeeRepository.deleteById(id);
    }
}
