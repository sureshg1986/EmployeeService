package com.employee.service.utils;

import static java.util.stream.Collectors.toList;

import org.springframework.stereotype.Component;

import com.employee.service.exceptions.GenericException;
import com.employee.service.model.Employee;

@Component
public class Validator {

	public String validateEmployee(Employee employee) throws GenericException{
	
		StringBuilder employeeExceptionMessage = new StringBuilder("");
		if(!EmployeeUtils.FormatValidator.isDateValidMMddyyyy(employee.getDob())){
			employeeExceptionMessage.append("DOB date invalid.. Pls try as MMddyyyy");
		}
		if(!EmployeeUtils.FormatValidator.isWholeNumberValid(employee.getJoiningYear())){
			employeeExceptionMessage.append("Joining Year invalid...");
		}
		return employeeExceptionMessage.toString();
	}
}
