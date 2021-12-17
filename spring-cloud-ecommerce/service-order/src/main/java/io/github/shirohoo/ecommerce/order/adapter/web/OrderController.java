package io.github.shirohoo.ecommerce.order.adapter.web;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.github.shirohoo.ecommerce.order.domain.Order;
import io.github.shirohoo.ecommerce.order.service.OrderCrudService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class OrderController {

    private final OrderCrudService orderCrudService;

    @GetMapping("/{username}")
    public ResponseEntity<List<ResponseOrder>> getOrderByUsername(@PathVariable String username) {
        return ResponseEntity.ok(
            orderCrudService.findByUsername(username)
                .stream()
                .map(ResponseOrder::convert)
                .collect(Collectors.toUnmodifiableList())
        );
    }

    @PostMapping("/{username}")
    public ResponseEntity<ResponseOrder> create(@PathVariable String username, @RequestBody RequestOrder requestOrder) {
        Order savedOrder = orderCrudService.save(requestOrder.toOrder(username));
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(ResponseOrder.convert(savedOrder));
    }

    @Value(staticConstructor = "of")
    public static class RequestOrder {

        String productId;
        Integer quantity;
        Long unitPrice;

        public Order toOrder(String username) {
            return Order.builder()
                .productId(productId)
                .quantity(quantity)
                .unitPrice(unitPrice)
                .totalPrice(quantity * unitPrice)
                .username(username)
                .build();
        }

    }

    @JsonInclude(Include.NON_NULL)
    @Value(staticConstructor = "of")
    public static class ResponseOrder {

        String productId;
        Integer quantity;
        Long unitPrice;
        Long totalPrice;
        String username;
        String orderId;
        LocalDateTime createdAt;

        @Builder
        private ResponseOrder(String productId, Integer quantity, Long unitPrice, Long totalPrice, String username, String orderId, LocalDateTime createdAt) {
            this.productId = productId;
            this.quantity = quantity;
            this.unitPrice = unitPrice;
            this.totalPrice = totalPrice;
            this.username = username;
            this.orderId = orderId;
            this.createdAt = createdAt;
        }

        public static ResponseOrder convert(Order order) {
            return ResponseOrder.builder()
                .productId(order.getProductId())
                .quantity(order.getQuantity())
                .unitPrice(order.getUnitPrice())
                .totalPrice(order.getTotalPrice())
                .username(order.getUsername())
                .orderId(order.getOrderId())
                .createdAt(order.getCreatedAt())
                .build();
        }

    }

}
