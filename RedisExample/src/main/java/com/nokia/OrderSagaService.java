package com.nokia;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderSagaService {

    private final InventoryService inventoryService;
    private final OrderRepository orderRepository;

    public OrderSagaService(InventoryService inventoryService,
                            OrderRepository orderRepository) {
        this.inventoryService = inventoryService;
        this.orderRepository = orderRepository;
    }

    @Transactional
    public String placeOrder(Long productId) {

        // Step 1: Redis atomic reserve
        if (!inventoryService.reserve(productId)) {
            return "❌ Out of Stock";
        }

        try {
            // Step 2: Save order in DB
            Order order = new Order();
            order.setProductId(productId);
            order.setStatus("CONFIRMED");

            orderRepository.save(order);

            return "✅ Order Placed";

        } catch (Exception ex) {
            // Step 3: Compensation (Saga rollback)
            inventoryService.rollback(productId);
            return "❌ Failed - Inventory Restored";
        }
    }
}

