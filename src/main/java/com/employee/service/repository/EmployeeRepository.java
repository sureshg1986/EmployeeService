package com.employee.service.repository;

import org.springframework.data.repository.CrudRepository;

import com.employee.service.model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

	Employee findByEmpId(Long empId);
	
}