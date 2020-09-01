package com.cognizant.kafkainsurer;

import com.cognizant.kafkainsurer.model.Policy;
import com.cognizant.kafkainsurer.service.PolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@RequestMapping(value = "/policy-kafka")
@ComponentScan
@RestController
public class KafkaInsurerApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaInsurerApplication.class, args);
	}

	@Autowired
	PolicyService policyService;

	@RequestMapping(method = RequestMethod.POST, value = "/newSubmission")
	@ResponseStatus(value = HttpStatus.CREATED)
	public String create(@RequestBody Policy policy) {
		policy = policyService.newSubmission(policy);
		return policy + " was submitted!!!";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/book/{policyNumber}")
	@ResponseStatus(value = HttpStatus.CREATED)
	public String book(@PathVariable("policyNumber") String policyNumber ) {
		policyService.book(policyNumber);
		return "Booking sent!";
	}

	@RequestMapping(method = RequestMethod.GET,produces= MediaType.APPLICATION_JSON_VALUE)
	public List<Policy> fetchAll() {
		return this.policyService.findAll();
	}
}