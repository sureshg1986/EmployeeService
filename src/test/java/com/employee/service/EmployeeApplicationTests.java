package com.employee.service;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.mockito.Mockito;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.employee.service.model.Address;
import com.employee.service.model.Employee;
import com.employee.service.repository.EmployeeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeApplicationTests {

	EmployeeRepository employeeRepositoryMock = mock(EmployeeRepository.class);
	
	@Autowired
    private WebApplicationContext wac;
	
	private MockMvc mockMvc;

	Employee employee = new Employee(1231L, "someName", new Address("Dallas"), "2007", "12/12/2012", "5000");


	@Before
	public void setUp() {
		Mockito.when(employeeRepositoryMock.save(employee)).thenReturn(employee);
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void addTester_success() {

		try {
			
			mockMvc.perform(
		            post("/add")
		                    .contentType(MediaType.APPLICATION_JSON)
		                    .content(asJsonString(employee)))
		            .andExpect(status().isOk());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void addTester_employeeNameMinTwoChars() {

		try {
			employee.setEmpName("s");
			mockMvc.perform(
		            post("/add")
		                    .contentType(MediaType.APPLICATION_JSON)
		                    .content(asJsonString(employee)))
		            .andExpect(status().isInternalServerError());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void addTester_nullObject() {

		try {
			employee=new Employee();
			mockMvc.perform(
		            post("/add")
		                    .contentType(MediaType.APPLICATION_JSON)
		                    .content(asJsonString(employee)))
		            .andExpect(status().isInternalServerError());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void addTester_invalidDOB() {

		try {
			employee.setDob("12");
			MvcResult mockResult= mockMvc.perform(
		            post("/add")
		                    .contentType(MediaType.APPLICATION_JSON)
		                    .content(asJsonString(employee)))
		            .andExpect(status().isInternalServerError())
		            .andReturn();
		    
			assertTrue(mockResult.getResponse().getContentAsString().contains("DOB date invalid"));        
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void editTester_employeeNameMinTwoChars() {

		try {
			employee.setEmpName("s");
			mockMvc.perform(
		            put("/edit")
		                    .contentType(MediaType.APPLICATION_JSON)
		                    .content(asJsonString(employee)))
		            .andExpect(status().isInternalServerError());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void editTester_nullObject() {

		try {
			employee=new Employee();
			mockMvc.perform(
		            put("/edit")
		                    .contentType(MediaType.APPLICATION_JSON)
		                    .content(asJsonString(employee)))
		            .andExpect(status().isInternalServerError());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void editTester_invalidDOB() {

		try {
			employee.setDob("12");
			MvcResult mockResult= mockMvc.perform(
					put("/edit")
		                    .contentType(MediaType.APPLICATION_JSON)
		                    .content(asJsonString(employee)))
		            .andExpect(status().isInternalServerError())
		            .andReturn();
		    
			assertTrue(mockResult.getResponse().getContentAsString().contains("DOB date invalid"));        
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void deleteTester_employeeNameMinTwoChars() {

		try {
			employee.setEmpName("s");
			mockMvc.perform(
					delete("/delete")
		                    .contentType(MediaType.APPLICATION_JSON)
		                    .content(asJsonString(employee)))
		            .andExpect(status().isInternalServerError());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void deleteTester_success() {

		try {
			mockMvc.perform(
					delete("/delete")
		                    .contentType(MediaType.APPLICATION_JSON)
		                    .content(asJsonString(employee)))
		            .andExpect(status().isOk());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void deleteTester_nullObject() {

		try {
			employee=new Employee();
			mockMvc.perform(
		            delete("/delete")
		                    .contentType(MediaType.APPLICATION_JSON)
		                    .content(asJsonString(employee)))
		            .andExpect(status().isInternalServerError());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void deleteTester_invalidDOB() {

		try {
			employee.setDob("12");
			MvcResult mockResult= mockMvc.perform(
					delete("/delete")
		                    .contentType(MediaType.APPLICATION_JSON)
		                    .content(asJsonString(employee)))
		            .andExpect(status().isInternalServerError())
		            .andReturn();
		    
			assertTrue(mockResult.getResponse().getContentAsString().contains("DOB date invalid"));        
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
}
