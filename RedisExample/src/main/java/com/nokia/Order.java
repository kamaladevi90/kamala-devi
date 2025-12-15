package com.nokia;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue
    private Long id;

    private Long productId;
    private String status;

    public Long getId() { return id; }
    public Long getProductId() { return productId; }
    public String getStatus() { return status; }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
