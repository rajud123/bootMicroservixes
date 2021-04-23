package com.raju.service;

import com.raju.entity.Employee;

public interface EmployeeService {
public Employee saveEmp(Employee emp);
public String deleteEmp(Integer id);
public Employee updateEmp(Employee emp);
}
