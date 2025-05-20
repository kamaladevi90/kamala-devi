package com.example;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    @GetMapping("/payment/api")
    public String payment() {
        return "Payment service is working!";
    }
}
