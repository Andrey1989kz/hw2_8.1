package com.example.hw2_8_1.service;

import com.example.hw2_8_1.model.Employee;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service

public class DepartmentsServiceImpl implements DepartmentsService {
    private final String ERROR_EMPLOYEE_NOT_FOUND = "Сотрудник нет в списке!";
    private final List<Employee> employees;

    public DepartmentsServiceImpl(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public Employee minSalary(int department) {
        return employees.stream()
                .filter(e -> e.getDepartment() == department)
                .min(Comparator.comparingInt(Employee::getSalary))
                .orElseThrow(() -> new RuntimeException(ERROR_EMPLOYEE_NOT_FOUND));
    }

    @Override
    public Employee maxSalary(int department) {
        return employees.stream()
                .filter(e -> e.getDepartment() == department)
                .max(Comparator.comparingInt(Employee::getSalary))
                .orElseThrow(() -> new RuntimeException(ERROR_EMPLOYEE_NOT_FOUND));
    }

    @Override
    public List<Employee> department(int department) {
        return employees.stream()
                .filter(e -> e.getDepartment() == department)
                .collect(Collectors.toList());
    }

    @Override
    public List<Employee> printAll() {
        return Collections.unmodifiableList(employees);
    }

    @Override
    public List<Employee> myEmployees() {
        employees.add(new Employee("Lev", "Tolstoy", 80_000, 5));
        employees.add(new Employee("Nikolay", "Gogol", 10_000, 5));
        employees.add(new Employee("Aleksandr", "Pushkin", 20_000, 1));
        employees.add(new Employee("Mihail", "Lermontov", 100_000, 1));
        employees.add(new Employee("Fedor", "Dostoevsky", 120_000, 2));
        employees.add(new Employee("Aleksandr", "Griboedov", 30_000, 2));
        employees.add(new Employee("Nikolay", "Nekrasov", 65000, 3));
        employees.add(new Employee("Anton", "Chehov", 155000, 3));
        employees.add(new Employee("Fedor", "Tutchev", 255000, 4));
        employees.add(new Employee("Afanasy", "Fet", 355000, 4));

        return employees;
    }
}
