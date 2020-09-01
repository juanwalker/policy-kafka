package com.cognizant.kafkainsurer.service;

import com.cognizant.kafkainsurer.model.Policy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PolicyService {

    @Autowired
    private KafkaTemplate<String, String> kafkaNewSubmission;

    @Autowired
    private KafkaTemplate<String, String> kafkaBooked;

    @Autowired
    private KafkaTemplate<String, String> kafkaLog;


    @Autowired
    private PolicyRepository repository;


    public Policy newSubmission(Policy policy) {
        kafkaLog.send(AppConstants.LOG_TOPIC_NAME, "newSubmission( "  + policy +  ") method called ");
        policy.setStatus("In progress");
        policy = this.repository.save(policy);
        kafkaLog.send(AppConstants.LOG_TOPIC_NAME, "Policy saved : " + policy);
        return policy;
    }



    public List<Policy> findAll() {
        kafkaLog.send(AppConstants.LOG_TOPIC_NAME, "findAll() policies method called ");
        return this.repository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }


    public void book(String policyNumber) {
        kafkaLog.send(AppConstants.LOG_TOPIC_NAME, "book( "  + policyNumber +  ") method called ");
        Policy policy = this.repository.findByPolicyNumberQuery(policyNumber);
        policy.setStatus("Awaiting Booking Validation");
        policy = this.repository.save(policy);
        kafkaLog.send(AppConstants.BOOK_TOPIC,policyNumber);

    }


    @KafkaListener(topics = AppConstants.BOOK_CALLBACK_TOPIC,
            groupId = AppConstants.GROUP_ID)
    public void bookedCallBack(String policyNumber) {
        kafkaLog.send(AppConstants.LOG_TOPIC_NAME, "bookedCallBack( "  + policyNumber +  ") method called ");
        Policy policy = this.repository.findByPolicyNumberQuery(policyNumber);
        policy.setStatus("Booked");
        this.repository.save(policy);
        kafkaLog.send(AppConstants.LOG_TOPIC_NAME, "Policy booked : " + policyNumber);

    }


}