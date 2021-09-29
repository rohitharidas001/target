package com.example.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.demo.entity.Target;

public interface TargetRepository extends MongoRepository<Target, Long> {

}
