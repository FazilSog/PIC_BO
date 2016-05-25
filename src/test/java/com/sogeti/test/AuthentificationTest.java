/*package com.sogeti.test;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.sogeti.bo.IMembreBO;
import com.sogeti.controller.MembreController;
import com.sogeti.dto.MembreDTO;


@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration
public class AuthentificationTest {
	
	@Mock
	private IMembreBO membreBO;
	
	@InjectMocks
	private MembreController membreController;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup()
	{
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(membreController).build();
	}	
	
	@Test
	public void testList() throws Exception {
		List<MembreDTO> listeMembreDTO = new ArrayList<MembreDTO>();
		listeMembreDTO.add(new MembreDTO());
		listeMembreDTO.add(new MembreDTO());
		
		Mockito.when(membreBO.listerMembres()).thenReturn((List<MembreDTO>) listeMembreDTO);
		
		 mockMvc.perform(
	                get("/membres"))
	                .andExpect(MockMvcResultMatchers.status().isOk())
	                .andExpect(view().name("membreDTO"))
		 			.andExpect(model().attributeExists("membreDTO"))
	                .andExpect(model().attribute("membreDTO", hasSize(5)));
	}

	@Test
    public void testGet() throws Exception {

        int userId = 3;

        mockMvc.perform(
                get("/membre" + userId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("password", is(1234)))
                .andExpect(jsonPath("username", is("sogeti")))
                .andExpect(jsonPath("role", is("d")))
                .andExpect(jsonPath("idMembre", is(userId)))
                .andExpect(jsonPath("status", is(true)));
    }
	
	@Test
    public void testSave() throws Exception {
        mockMvc.perform(
                post("/membre")
                        .contentType(TestUtil.APPLICATION_JSON_UTF8)
                        .content(TestUtil.convertObjectToJsonBytes(new MembreDTO()))
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("success", is(true)));
    }
	
	 @Test
	    public void testUpdate() throws Exception {
	        mockMvc.perform(
	                put("/Membre")
	                        .contentType(TestUtil.APPLICATION_JSON_UTF8)
	                        .content(TestUtil.convertObjectToJsonBytes(new MembreDTO())))
	                .andExpect(MockMvcResultMatchers.status().isOk())
	                .andExpect(jsonPath("success", is(true)));
	    }

	    @Test
	    public void testDelete() throws Exception {
	        mockMvc.perform(
	                delete("/user/3"))
	                .andExpect(MockMvcResultMatchers.status().isOk())
	                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
	                .andExpect(jsonPath("success", is(true)));
	    }
}

*/