package io.github.shirohoo.ecommerce.order.domain;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Order {

    private String productId;
    private final LocalDateTime createdAt;
    private Integer quantity;
    private Long unitPrice;
    private String username;
    private String orderId;
    private Long totalPrice;

    @Builder
    private Order(String productId, Integer quantity, Long unitPrice, Long totalPrice, String username, String orderId, LocalDateTime createdAt) {
        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
        this.username = username;

        if (orderId == null) {
            this.orderId = UUID.randomUUID().toString();
        } else {
            this.orderId = orderId;
        }

        this.createdAt = createdAt;
    }

}
