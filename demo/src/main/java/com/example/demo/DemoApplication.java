package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.demo.entity.Target;
import com.example.demo.repository.TargetRepository;
import com.example.demo.entity.CurrentPrice;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {
	
	@Autowired
	TargetRepository targetRepository;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {

		targetRepository.deleteAll();

		targetRepository.save(new Target(152673487L, "The Big Lebowski (Blu-ray) (Widescreen)", new CurrentPrice(13.49, "USD")));
		targetRepository.save(new Target(152894382L, "Orange", new CurrentPrice(2.49, "USD")));
	}

}
