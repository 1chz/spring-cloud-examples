package io.github.shirohoo.ecommerce.order.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Order {

    private String productId;
    private int quantity;
    private long unitPrice;
    private long totalPrice;
    private String username;
    private String orderId;

    @Builder
    private Order(String productId, int quantity, long unitPrice, long totalPrice, String username, String orderId) {
        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
        this.username = username;
        this.orderId = orderId;
    }

}
