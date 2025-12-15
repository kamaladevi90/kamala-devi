package com.nokia;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderSagaService sagaService;

    public OrderController(OrderSagaService sagaService) {
        this.sagaService = sagaService;
    }

    @PostMapping("/{productId}")
    public String placeOrder(@PathVariable Long productId) {
        return sagaService.placeOrder(productId);
    }
}

