package com.springboot.eurekaclient;

import com.springboot.eurekaclient.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableEurekaClient
public class EurekaClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaClientApplication.class, args);
	}
}



@RestController
class CustomerService {
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Autowired
	RestTemplate restTemplate;

	protected String serviceUrl = "http://CUSTOMER-SERVICE"; // CUSTOMER-SERVICE is the name of the microservice we're calling

	@RequestMapping("/getCustomerList")
	public List<Customer> getCustomerList() {
		ArrayList objectList = restTemplate.getForObject(serviceUrl + "/customer/list", ArrayList.class);
		return objectList;
	}
}