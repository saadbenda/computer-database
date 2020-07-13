package controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import dto.ComputerDto;
import mapper.MapperDto;
import model.Computer;

public class AddComputerTester {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext context;
	
	@Autowired
	private MapperDto mapperDto;
	
	@Autowired
	ComputerDto computer;
	
	ObjectMapper om = new ObjectMapper();
	

	@Before
	private void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	
	
	@Test
	public void doPost() throws Exception {
		 computer.setId("");
		 computer.setIntroduced("");
		 computer.setDiscontinued("");
		 computer.setCompanyDto(null);
	
		String jsonRequest = om.writeValueAsString(computer);
		
		MvcResult result = mockMvc.perform(post("/addComputer").content(jsonRequest).content(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andReturn();
		String resultContent = result.getResponse().getContentAsString();
		System.out.println(resultContent);
		//Assert.assertTrue(condition);
	}
	
	@Test
	public void doGet() throws Exception {
		MvcResult result = mockMvc.perform(get("/addComputer").content(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andReturn();
	}
	
	

}
