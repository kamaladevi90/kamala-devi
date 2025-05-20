package com.example;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import org.springframework.context.annotation.Bean;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;

@RestController
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @GetMapping("/order/api")
    public String order() {
        // Call payment-service by its registered name
        String paymentResponse = restTemplate.getForObject("http://payment-service/payment/api", String.class);
        return "Order Service calling -> " + paymentResponse;
    }
}
