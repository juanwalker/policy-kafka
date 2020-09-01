package com.cognizant.kafkalog.service;


import com.cognizant.kafkalog.model.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogService {

    @Autowired
    private KafkaTemplate<String, String> kafkaLog;

    @Autowired
    private LogRepository repository;




    @KafkaListener(topics = AppConstants.LOG_TOPIC_NAME,
            groupId = AppConstants.GROUP_ID)
    public Log newLogEntry(String text) {

        return repository.save(new Log(text));

    }

    public List<Log> findAll() {
        return this.repository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

}