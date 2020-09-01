package com.cognizant.kafkainsurer.service;


import com.cognizant.kafkainsurer.model.Policy;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PolicyRepository   extends MongoRepository<Policy, String> {

    @Query("{policyNumber : ?0}")
    public Policy findByPolicyNumberQuery(String policyNumber);

}


