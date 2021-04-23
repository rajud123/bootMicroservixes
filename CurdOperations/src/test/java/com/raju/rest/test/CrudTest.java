package com.raju.rest.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.net.MediaType;
import com.raju.entity.Education;
import com.raju.entity.Employee;
import com.raju.entity.PersonalInfo;
import com.raju.repo.EmployeeRepo;
import com.raju.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.mockito.BDDMockito.given;
public class CrudTest {

	@MockBean
	private EmployeeService service;
	
	@MockBean
	private EmployeeRepo repo;
	@Autowired
	private MockMvc mockMvc;
	@Test
	public void saveEmployee() throws Exception {
		Employee emp=new Employee();
		PersonalInfo pInfo=new PersonalInfo();
		pInfo.setFirstName("David");
		pInfo.setLastName("Dobrik");
		pInfo.setGender("male");
		pInfo.setMaritalStatus("single");
		pInfo.setDob(LocalDate.of(1995, 11, 12));
		
		Education ed1=new Education();
		ed1.setAddress("hyd");
		ed1.setInstitution("TKR");
		ed1.setStartDate(LocalDate.of(2013, 8, 25));
		ed1.setEndDate(LocalDate.of(2017, 05, 12));
		ed1.setType("BTECH");
		
		Education ed2=new Education();
		ed2.setAddress("hyd");
		ed2.setInstitution("KJC");
		ed2.setStartDate(LocalDate.of(2011, 8, 25));
		ed2.setEndDate(LocalDate.of(2013, 05, 12));
		ed2.setType("Intermediate");
		
		emp.setDept("IT");
		emp.setEmployementId(101);
		emp.setReportingManager("RAM");
		emp.setPersonalInfo(pInfo);
		
		Set<Education> eds=new HashSet<Education>();
		eds.add(ed1);
		eds.add(ed2);
		emp.setEducation(eds);
		
		when(service.saveEmp(emp)).thenReturn(emp);
		ObjectMapper mapper=new ObjectMapper();
		
		String valueAsString = mapper.writeValueAsString(emp);
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/saveUser").contentType("application/json").content(valueAsString);
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		int status = mvcResult.getResponse().getStatus();
		String contentAsString = mvcResult.getResponse().getContentAsString();
		assertEquals(201, status);
		assertEquals("Employee created successfully", contentAsString);
		
		
		
	}
	
	@Test
	public void shouldNotSaveEmployee() throws Exception {
		Employee emp=new Employee();
		PersonalInfo pInfo=null;
		
		Education ed1=new Education();
		ed1.setAddress("hyd");
		ed1.setInstitution("TKR");
		ed1.setStartDate(LocalDate.of(2013, 8, 25));
		ed1.setEndDate(LocalDate.of(2017, 05, 12));
		ed1.setType("BTECH");
		
		Education ed2=new Education();
		ed2.setAddress("hyd");
		ed2.setInstitution("KJC");
		ed2.setStartDate(LocalDate.of(2011, 8, 25));
		ed2.setEndDate(LocalDate.of(2013, 05, 12));
		ed2.setType("Intermediate");
		
		emp.setDept("IT");
		emp.setEmployementId(101);
		emp.setReportingManager("RAM");
		emp.setPersonalInfo(pInfo);
		
		Set<Education> eds=new HashSet<Education>();
		eds.add(ed1);
		eds.add(ed2);
		emp.setEducation(eds);
		
		when(service.saveEmp(emp)).thenReturn(null);
		ObjectMapper mapper=new ObjectMapper();
		
		String valueAsString = mapper.writeValueAsString(emp);
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/saveUser").contentType("application/json").content(valueAsString);
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		int status = mvcResult.getResponse().getStatus();
		String contentAsString = mvcResult.getResponse().getContentAsString();
		assertEquals(400, status);
		assertEquals("Employee not created", contentAsString);
		
	}
	
	@Test
	public void shouldUpdateEmp() {
		
		//modify marital status
		Integer id=101;
		Employee emp=new Employee();
		PersonalInfo pInfo=new PersonalInfo();
		pInfo.setFirstName("David");
		pInfo.setLastName("Dobrik");
		pInfo.setGender("male");
		pInfo.setMaritalStatus("single");
		pInfo.setDob(LocalDate.of(1995, 11, 12));
		
		Education ed1=new Education();
		ed1.setAddress("hyd");
		ed1.setInstitution("TKR");
		ed1.setStartDate(LocalDate.of(2013, 8, 25));
		ed1.setEndDate(LocalDate.of(2017, 05, 12));
		ed1.setType("BTECH");
		
		Education ed2=new Education();
		ed2.setAddress("hyd");
		ed2.setInstitution("KJC");
		ed2.setStartDate(LocalDate.of(2011, 8, 25));
		ed2.setEndDate(LocalDate.of(2013, 05, 12));
		ed2.setType("Intermediate");
		
		emp.setDept("IT");
		emp.setEmployementId(101);
		emp.setReportingManager("RAM");
		emp.setPersonalInfo(pInfo);
		
		Set<Education> eds=new HashSet<Education>();
		eds.add(ed1);
		eds.add(ed2);
		emp.setEducation(eds);
		
		given(repo.findById(id)).willReturn(Optional.of(emp));
		assertThrows(RuntimeException.class, ()->{
			Employee emp1 = repo.findById(id).get();
			PersonalInfo pInfo1=new PersonalInfo();
			pInfo1.setFirstName(pInfo.getFirstName());
			pInfo1.setLastName(pInfo.getLastName());
			pInfo1.setGender(pInfo.getGender());
			pInfo1.setMaritalStatus("married");
			pInfo1.setDob(pInfo.getDob());
			
			Education edc1=new Education();
			edc1.setAddress(ed1.getAddress());
			edc1.setInstitution(ed1.getInstitution());
			edc1.setStartDate(ed1.getStartDate());
			edc1.setEndDate(ed1.getEndDate());
			edc1.setType(ed1.getType());
			
			Education edc2=new Education();
			ed2.setAddress(ed2.getAddress());
			ed2.setInstitution(ed2.getInstitution());
			ed2.setStartDate(ed2.getStartDate());
			ed2.setEndDate(ed2.getEndDate());
			ed2.setType(ed2.getType());
			
			emp1.setDept(emp.getDept());
			emp1.setEmployementId(emp.getEmployementId());
			emp1.setReportingManager(emp.getReportingManager());
			emp1.setPersonalInfo(pInfo);
			
			Set<Education> newEds=new HashSet<Education>();
			newEds.add(ed1);
			newEds.add(ed2);
			emp1.setEducation(eds);
			
			when(service.saveEmp(emp1)).thenReturn(emp1);
			ObjectMapper mapper=new ObjectMapper();
			
			String valueAsString = mapper.writeValueAsString(emp1);
			MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/updateEmp").contentType("application/json").content(valueAsString);
			MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
			int status = mvcResult.getResponse().getStatus();
			String contentAsString = mvcResult.getResponse().getContentAsString();
			assertEquals(400, status);
			assertEquals("Employee not found", contentAsString);
			
			
		});
		
	}

	@Test
	public void shouldNotUpdateEmp() {
		
		//modify marital status
		Integer id=101;
		given(repo.findById(id)).willReturn(Optional.empty());
		assertThrows(RuntimeException.class, ()->{
			Employee emp = repo.findById(id).get();
			PersonalInfo pInfo=new PersonalInfo();
			pInfo.setFirstName(pInfo.getFirstName());
			pInfo.setLastName(pInfo.getLastName());
			pInfo.setGender(pInfo.getGender());
			pInfo.setMaritalStatus("married");
			pInfo.setDob(pInfo.getDob());
			
			Education ed1=new Education();
			ed1.setAddress(ed1.getAddress());
			ed1.setInstitution(ed1.getInstitution());
			ed1.setStartDate(ed1.getStartDate());
			ed1.setEndDate(ed1.getEndDate());
			ed1.setType(ed1.getType());
			
			Education ed2=new Education();
			ed2.setAddress(ed2.getAddress());
			ed2.setInstitution(ed2.getInstitution());
			ed2.setStartDate(ed2.getStartDate());
			ed2.setEndDate(ed2.getEndDate());
			ed2.setType(ed2.getType());
			
			emp.setDept(emp.getDept());
			emp.setEmployementId(emp.getEmployementId());
			emp.setReportingManager(emp.getReportingManager());
			emp.setPersonalInfo(pInfo);
			
			Set<Education> eds=new HashSet<Education>();
			eds.add(ed1);
			eds.add(ed2);
			emp.setEducation(eds);
			
			when(service.saveEmp(emp)).thenReturn(emp);
			ObjectMapper mapper=new ObjectMapper();
			
			String valueAsString = mapper.writeValueAsString(emp);
			MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/updateEmp").contentType("application/json").content(valueAsString);
			MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
			int status = mvcResult.getResponse().getStatus();
			String contentAsString = mvcResult.getResponse().getContentAsString();
			assertEquals(400, status);
			assertEquals("Employee not found", contentAsString);
			
			
		});
		
	}
	
	
}
