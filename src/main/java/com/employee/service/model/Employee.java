package com.employee.service.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.gemfire.mapping.annotation.Region;
import org.springframework.format.annotation.DateTimeFormat;

@Region("employee")
public class Employee {

	@Id
	private Long empId;
	@NotEmpty(message="Employee Name should not be empty")
	@Size(min=2, message="Employee Name should have atleast 2 characters")
	private String empName;
	private Address address;
	@Size(max=4, min=4, message="Please enter joiningYear as YYYY")
	private String joiningYear;
	@NotEmpty
	private String dob;
	@NotEmpty
	@Size(min=4, message="Please enter valid Salary")
	private String salary;

	public Employee(){
		
	}
	
	@PersistenceConstructor
	public Employee(Long empId, String empName, Address address, String joiningYear, String dob, String salary) {
		this.empId = empId;
		this.empName = empName;
		this.address = address;
		this.joiningYear = joiningYear;
		this.dob = dob;
		this.salary = salary;
	}

	public Long getEmpId() {
		return empId;
	}

	public void setEmpId(Long empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getJoiningYear() {
		return joiningYear;
	}

	public void setJoiningYear(String joiningYear) {
		this.joiningYear = joiningYear;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	@Override
	public String toString(){
		return empId+" "+empName+" ";
		
		
	}
}
