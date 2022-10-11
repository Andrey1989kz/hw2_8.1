package com.example.hw2_8_1.service;

import com.example.hw2_8_1.model.Employee;

import java.util.List;

public interface EmployeeService {
    Employee addEmployee(String firstName, String lastName, int salary, int department);

    Employee removeEmployee(String firstName, String lastName);

    Employee findEmployee(String firstName, String lastName);

    Employee getLowestPaidEmployee(int department);

    Employee getHighestPaidEmployee(int department);

    List<Employee> printEmployeesForDepartment(int department);

    List<Employee> printEmployeesByDepartments();

    List<Employee> printEmployees();

    List<Employee> fillEmployeesList();
}

