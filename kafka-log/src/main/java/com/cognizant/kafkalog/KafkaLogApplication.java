package com.cognizant.kafkalog;

import com.cognizant.kafkalog.model.Log;
import com.cognizant.kafkalog.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.util.List;

@SpringBootApplication
@RequestMapping(value = "/log-kafka")
@ComponentScan
@RestController
public class KafkaLogApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaLogApplication.class, args);
	}

	@Autowired
	LogService logService;

	@RequestMapping(method = RequestMethod.GET,produces= MediaType.APPLICATION_JSON_VALUE)
	public List<Log> fetchAll() {
		return this.logService.findAll();
	}
}
