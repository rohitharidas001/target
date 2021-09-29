package com.example.demo.service;

import static org.mockito.BDDMockito.given;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.example.demo.entity.CurrentPrice;
import com.example.demo.entity.Target;
import com.example.demo.repository.TargetRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
public class TargetServiceTest {
	
    @Mock
    private TargetRepository targetRepository;
    
    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private TargetService targetService;
    
    @Test
    public void getProductSuccessTest() {
    	Long id = 152894382L;
    	Target expectedTarget = new Target(152894382L, "Orange", new CurrentPrice(13.49, "USD"));
    	given(targetRepository.findById(id)).willReturn(Optional.of(expectedTarget));
    	Target actualTarget = targetService.getProduct(id);
    	
    	Assertions.assertEquals(expectedTarget, actualTarget);
    }
       
    @Test
    public void getProductFailureTest() {
    	Long id = 152894381L;
    	Target expectedTarget = null;
    	given(targetRepository.findById(id)).willReturn(Optional.empty());
    	Target actualTarget = targetService.getProduct(id);
    	
    	Assertions.assertEquals(expectedTarget, actualTarget);
    }
    
    @Test
    public void getExternalProductTest() {
    	String url = "https://redsky.target.com/v3/pdp/tcin/13860428?excludes=taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics&key=candidate#_blank";
    	String expectedResponse = "jsonData";
    	given(restTemplate.exchange(url, HttpMethod.GET, null, String.class)).willReturn(new ResponseEntity<>(expectedResponse, HttpStatus.OK));
    	String actualResponse = targetService.getExternalProduct();
    	
    	Assertions.assertEquals(expectedResponse, actualResponse);
    }
    
}
