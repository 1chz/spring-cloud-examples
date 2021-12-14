package io.github.shirohoo.ecommerce.order.service;

import io.github.shirohoo.ecommerce.order.domain.Order;
import java.util.List;

public interface OrderCrudService {

    Order save(Order requestOrder);

    Order findByOrderId(String orderId);

    List<Order> findByUsername(String username);

}
