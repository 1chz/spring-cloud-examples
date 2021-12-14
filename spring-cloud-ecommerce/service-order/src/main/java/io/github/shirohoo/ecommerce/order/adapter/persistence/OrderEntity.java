package io.github.shirohoo.ecommerce.order.adapter.persistence;

import io.github.shirohoo.ecommerce.order.domain.Order;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Getter
@Table(name = "ORDERS")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String productId;

    private int quantity;

    private long unitPrice;

    private long totalPrice;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false, unique = true)
    private String orderId;

    @Column(nullable = false, updatable = false)
    @ColumnDefault("CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @Builder
    private OrderEntity(String productId, int quantity, long unitPrice, long totalPrice, String username, String orderId, LocalDateTime createdAt) {
        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
        this.username = username;
        this.orderId = orderId;
        this.createdAt = createdAt;
    }

    public static OrderEntity convert(Order order) {
        return OrderEntity.builder()
            .productId(order.getProductId())
            .quantity(order.getQuantity())
            .unitPrice(order.getUnitPrice())
            .totalPrice(order.getTotalPrice())
            .username(order.getUsername())
            .orderId(order.getOrderId())
            .build();
    }

    public Order toOrder() {
        return Order.builder()
            .productId(productId)
            .quantity(quantity)
            .unitPrice(unitPrice)
            .totalPrice(totalPrice)
            .username(username)
            .orderId(orderId)
            .build();
    }

}
