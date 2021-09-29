package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.entity.Target;
import com.example.demo.repository.TargetRepository;

@Service
public class TargetService {
	
	@Autowired
	TargetRepository targetRepository;
	
	@Autowired
	RestTemplate restTemplate;
	
	public static final String url = "https://redsky.target.com/v3/pdp/tcin/13860428?excludes=taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics&key=candidate#_blank";

	public Target getProduct(Long id) {
		Optional<Target> targetData = targetRepository.findById(id);
		if(targetData.isPresent()) {
			return targetData.get();
		}
		return null;
	}
	
	public String getExternalProduct() {
		String externalData = restTemplate.exchange(url, HttpMethod.GET, null, String.class).getBody();
		return externalData;
	}
	
	public void updateProduct(Target oldData, Target newData) {
		oldData.setCurrentPrice(newData.getCurrentPrice());
		targetRepository.save(oldData);
	}
}
