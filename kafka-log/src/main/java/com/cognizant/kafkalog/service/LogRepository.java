package com.cognizant.kafkalog.service;


import com.cognizant.kafkalog.model.Log;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository   extends MongoRepository<Log, String> {



}


