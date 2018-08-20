package com.employee.service.controller;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.employee.service.aop.GenericLog;
import com.employee.service.exceptions.GenericException;
import com.employee.service.model.Employee;
import com.employee.service.repository.EmployeeRepository;
import com.employee.service.utils.Constants;
import com.employee.service.utils.EmployeeUtils;
import com.employee.service.utils.Validator;


@RestController
public class EmployeeController {

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	Validator validator;
	
	
	Log log = LogFactory.getLog(EmployeeController.class);

	@GenericLog
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public Object addEmployee(@Valid @RequestBody Employee employee, BindingResult bindingResult)
			throws GenericException {
		if (bindingResult.hasErrors()) {
			EmployeeUtils.throwBeanValidations(bindingResult, Constants.methodNames.apiAddEmployee);
		}
		String validatorException = validator.validateEmployee(employee);
		if(validatorException.length()!=0){
			EmployeeUtils.throwValidationsException(validatorException, Constants.methodNames.apiAddEmployee);
		}
		if(employeeRepository.findByEmpId(employee.getEmpId())!=null){
			EmployeeUtils.throwValidationsException("Employee Already available", Constants.methodNames.apiEditEmployee);
		}
		employeeRepository.save(employee);
		return ResponseEntity.status(HttpStatus.OK).body("SUCCESS");
	}

	@GenericLog
	@RequestMapping(value = "/edit", method = RequestMethod.PUT)
	public Object editEmployee(@Valid @RequestBody Employee employee, BindingResult bindingResult) throws GenericException {
		if (bindingResult.hasErrors()) {
			EmployeeUtils.throwBeanValidations(bindingResult, Constants.methodNames.apiEditEmployee);
		}
		String validatorException = validator.validateEmployee(employee);
		if(validatorException.length()!=0){
			EmployeeUtils.throwValidationsException(validatorException, Constants.methodNames.apiEditEmployee);
		}
		if(employeeRepository.findByEmpId(employee.getEmpId())==null){
			EmployeeUtils.throwValidationsException("No employee to edit", Constants.methodNames.apiEditEmployee);
		}
		
		employeeRepository.save(employee);
		return ResponseEntity.status(HttpStatus.OK).body("SUCCESS");
	}

	@GenericLog
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public Object deleteEmployee(@Valid @RequestBody Employee employee, BindingResult bindingResult) throws GenericException {
		if (bindingResult.hasErrors()) {
			EmployeeUtils.throwBeanValidations(bindingResult, Constants.methodNames.apiDeleteEmployee);
		}
		String validatorException = validator.validateEmployee(employee);
		if(validatorException.length()!=0){
			EmployeeUtils.throwValidationsException(validatorException, Constants.methodNames.apiDeleteEmployee);
		}
		employeeRepository.delete(employee);
		return ResponseEntity.status(HttpStatus.OK).body("SUCCESS");
	}

	@GenericLog
	@RequestMapping(value = "/getEmployee/{id}", method = RequestMethod.GET)
	public Object getEmployee(@PathVariable Long id) {
		Employee employee = employeeRepository.findByEmpId(id);
		log.info("id=" + id);
		return employee;
	}

}
