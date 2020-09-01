package com.cognizant.bridgelayerkafka.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookedService {

    @Autowired
    private KafkaTemplate<String, String> kafkaNewSubmission;
    @Autowired
    private KafkaTemplate<String, String> kafkaLog;

    @Autowired
    private KafkaTemplate<String, String> kafkaBooked;

    @KafkaListener(topics = AppConstants.BOOK_TOPIC,
            groupId = AppConstants.GROUP_ID)
    public void book(String policyNumber) {
        kafkaLog.send(AppConstants.LOG_TOPIC_NAME, "newSubmission( "  + policyNumber +  ") method called ");
        kafkaBooked.send(AppConstants.BOOK_CALLBACK_TOPIC, policyNumber);

    }




}