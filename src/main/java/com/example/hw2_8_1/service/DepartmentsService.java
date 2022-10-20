package com.example.hw2_8_1.service;

import com.example.hw2_8_1.model.Employee;

import java.util.List;

public interface DepartmentsService {

    Employee minSalary(int department);

    Employee maxSalary(int department);

    List<Employee> department(int department);

    List<Employee> printAll();

    List<Employee> myEmployees();
}
