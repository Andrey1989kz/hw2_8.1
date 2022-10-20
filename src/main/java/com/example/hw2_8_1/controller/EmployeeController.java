package com.example.hw2_8_1.controller;

import com.example.hw2_8_1.model.Employee;
import com.example.hw2_8_1.service.DepartmentsService;
import com.example.hw2_8_1.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;
    private final DepartmentsService departmentService;

    public EmployeeController(EmployeeService employeeService, DepartmentsService departmentService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
    }


    @GetMapping(path = "/add")
    public Object add(
            @RequestParam(value = "firstName") String firstName,
            @RequestParam(value = "lastName") String lastName,
            @RequestParam(value = "salary") int salary,
            @RequestParam(value = "department") int department) {
        Employee employee = null;
        try {
            employee = employeeService.add(firstName, lastName, salary, department);
        } catch (Throwable e) {
            return e.getMessage();
        }
        return employee;
    }

    @GetMapping(path = "/remove")
    public Object remove(
            @RequestParam(value = "firstName") String firstName,
            @RequestParam(value = "lastName") String lastName) {
        Employee employee = null;
        try {
            employee = employeeService.remove(firstName, lastName);
        } catch (Throwable e) {
            return e.getMessage();
        }
        return employee;
    }

    @GetMapping(path = "/find")
    public Object findEmployee(
            @RequestParam(value = "firstName") String firstName,
            @RequestParam(value = "lastName") String lastName) {
        Employee employee = null;
        try {
            employee = employeeService.find(firstName, lastName);
        } catch (Throwable e) {
            return e.getMessage();
        }
        return employee;
    }

    @GetMapping(path = "/departments/max-salary")
    public Object maxSalary(
            @RequestParam(value = "departmentId") int departmentId) {
        Employee employee = null;
        try {
            employee = departmentService.maxSalary(departmentId);
        } catch (Throwable e) {
            return e.getMessage();
        }
        return employee;
    }

    @GetMapping(path = "/departments/min-salary")
    public Object minSalary(
            @RequestParam(value = "departmentId") int departmentId) {
        Employee employee = null;
        try {
            employee = departmentService.minSalary(departmentId);
        } catch (Throwable e) {
            return e.getMessage();
        }
        return employee;
    }

    @GetMapping(path = "/departments/all", params = "dep-Id")
    public Object department(
            @RequestParam(value = "departmentId") int departmentId) {
        List<Employee> employees = null;
        try {
            employees = departmentService.department(departmentId);
        } catch (Throwable e) {
            return e.getMessage();
        }
        return employees;
    }

    @GetMapping(path = "/print")
    public Object printAll() {
        List<Employee> employees = null;
        try {
            employees = departmentService.printAll();
        } catch (Throwable e) {
            return e.getMessage();
        }
        return employees;
    }

    @GetMapping(path = "/myEmployee")
    public Object myEmployee() {
        List<Employee> employees = null;
        try {
            employees = departmentService.myEmployees();
        } catch (Throwable e) {
            return e.getMessage();
        }
        return employees;
    }
}