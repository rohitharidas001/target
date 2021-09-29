package com.example.demo.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.example.demo.entity.CurrentPrice;
import com.example.demo.entity.Target;
import com.example.demo.repository.TargetRepository;
import com.example.demo.service.TargetService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(TargetController.class)
public class TargetControllerTest {
	
    @Autowired
    private MockMvc mvc;

    @MockBean
    private TargetService targetService;
    
    @MockBean
    private TargetRepository targetRepository;
    
    @Test
    public void getProductTest() throws Exception {
    	
    	Long id = 152673487L;
    	Target target = new Target(152673487L, "The Big Lebowski (Blu-ray) (Widescreen)", new CurrentPrice(13.49, "USD"));
    	
    	given(targetService.getProduct(id)).willReturn(target);
    	
    	mvc.perform(get("/products/{id}", id)
    			.contentType(MediaType.APPLICATION_JSON))
    			.andExpect(status().isOk())
    			.andExpect(jsonPath("$.id").value(152673487L));	
    }
    
    @Test
    public void getProductFailureTest() throws Exception {
    	
    	Long id = 152673486L;
    	
    	given(targetService.getProduct(id)).willReturn(null);
    	
    	mvc.perform(get("/products/{id}", id)
    			.contentType(MediaType.APPLICATION_JSON))
    			.andExpect(status().isNotFound());	
    }
    
    @Test
    public void updateProductTest() throws Exception {
    	
    	Long id = 152673487L;
    	Target target = new Target(152673487L, "The Big Lebowski (Blu-ray) (Widescreen)", new CurrentPrice(13.49, "USD"));
    	Target newTarget = new Target(152673487L, "The Big Lebowski (Blu-ray) (Widescreen)", new CurrentPrice(16.49, "USD"));
    	ObjectMapper objectMapper = new ObjectMapper();
    	String jsonString = objectMapper.writeValueAsString(newTarget);
    	
    	given(targetService.getProduct(id)).willReturn(target);
    	
    	mvc.perform(put("/products/{id}", id)
    			.contentType(MediaType.APPLICATION_JSON).content(jsonString))
    			.andExpect(status().isOk());  	
    }

}
