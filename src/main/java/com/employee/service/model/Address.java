package com.employee.service.model;

import javax.validation.constraints.NotEmpty;

public class Address {

	private String addrLine1;
	private String addrLine2;
	@NotEmpty
	private String city;
	@NotEmpty
	private String zipCode;

	public Address() {

	}

	public Address(String city) {
		this("", "", city, "");
	}

	public Address(String addrLine1, String addrLine2, String city, String zipCode) {
		this.addrLine1 = addrLine1;
		this.addrLine2 = addrLine2;
		this.city = city;
		this.zipCode = zipCode;
	}

	public String getAddrLine1() {
		return addrLine1;
	}

	public void setAddrLine1(String addrLine1) {
		this.addrLine1 = addrLine1;
	}

	public String getAddrLine2() {
		return addrLine2;
	}

	public void setAddrLine2(String addrLine2) {
		this.addrLine2 = addrLine2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

}
