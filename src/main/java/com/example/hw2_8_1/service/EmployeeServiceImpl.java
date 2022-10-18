package com.example.hw2_8_1.service;

import com.example.hw2_8_1.exception.InvalidInputException;
import com.example.hw2_8_1.model.Employee;
import com.example.hw2_8_1.service.EmployeeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringUtils.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final List<Employee> employees;
    private final String ERR_EMPL_ALREADY_ADDED = "Сотрудник уже в списке!";
    private final String ERR_EMPL_NOT_FOUND = "Сотрудник нет в списке!";

    public EmployeeServiceImpl(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public Employee add(String firstName, String lastName, int salary, int department) {
        validateInput(firstName,lastName);
        Employee employee = new Employee(firstName, lastName, salary, department);
        if (employees.contains(employee)) {
            throw new RuntimeException(ERR_EMPL_ALREADY_ADDED);
        }
        employees.add(employee);
        return employee;
    }

    @Override
    public Employee remove(String firstName, String lastName) {
        validateInput(firstName,lastName);
        Employee employee = find(firstName, lastName);
        employees.remove(employee);
        return employee;
    }

    @Override
    public Employee find(String firstName, String lastName) {
       validateInput(firstName,lastName);
        final Optional<Employee> employee = employees.stream()
                .filter(e -> e.getFirstName().equals(firstName) && e.getLastName().equals(lastName))
                .findAny();
        return employee.orElseThrow(() -> new RuntimeException(ERR_EMPL_NOT_FOUND));
    }

    @Override
    public Employee minSalary(int department) {
        return employees.stream()
                .filter(e -> e.getDepartment() == department)
                .min(Comparator.comparingInt(e -> e.getSalary()))
                .orElseThrow(() -> new RuntimeException(ERR_EMPL_NOT_FOUND));
    }

    @Override
    public Employee maxSalary(int department) {
        return employees.stream()
                .filter(e -> e.getDepartment() == department)
                .max(Comparator.comparingInt(e -> e.getSalary()))
                .orElseThrow(() -> new RuntimeException(ERR_EMPL_NOT_FOUND));
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
    private void validateInput (String firstName, String lastName) {
        if (!(isAlpha(firstName) && isAlpha(lastName))) {
            throw new InvalidInputException();
        }
    }
}