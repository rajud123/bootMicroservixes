package com.raju.service;

import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raju.entity.Education;
import com.raju.entity.Employee;
import com.raju.entity.PersonalInfo;
import com.raju.repo.EmployeeRepo;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepo repo;

	@Override
	public Employee saveEmp(Employee emp) {
		return repo.save(emp);
	}

	@Override
	public String deleteEmp(Integer id) {
		Employee employee = repo.findById(id).get();
		if (employee != null) {
			repo.deleteById(id);
			return "Employee with id::" + id + " is removed";
		} else {
			return "No data found";
		}
	}

	@Override
	public Employee updateEmp(Employee emp) {
		Employee employee = repo.findById(emp.getEid()).orElse(null);
		employee.setPersonalInfo(employee.getPersonalInfo());
		Set set = new HashSet<Education>();
		for (Education ed1 : employee.getEducation()) {
			set.add(ed1);
		}
		employee.setEducation(set);
		return employee;
	}

}
