package io.github.shirohoo.ecommerce.order.adapter.persistence;

import io.github.shirohoo.ecommerce.order.domain.Order;
import java.util.List;

public interface OrderCrudRepository {

    Order save(Order order);

    Order findByOrderId(String orderId);

    List<Order> findByUsername(String username);

}
