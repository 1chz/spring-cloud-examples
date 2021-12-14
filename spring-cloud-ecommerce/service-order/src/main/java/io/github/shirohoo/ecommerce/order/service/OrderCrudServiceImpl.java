package io.github.shirohoo.ecommerce.order.service;

import io.github.shirohoo.ecommerce.order.adapter.persistence.OrderCrudRepository;
import io.github.shirohoo.ecommerce.order.domain.Order;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderCrudServiceImpl implements OrderCrudService {

    private final OrderCrudRepository orderCrudRepository;

    @Override
    public Order save(Order requestOrder) {
        return orderCrudRepository.save(requestOrder);
    }

    @Override
    public Order findByOrderId(String orderId) {
        return orderCrudRepository.findByOrderId(orderId);
    }

    @Override
    public List<Order> findByUsername(String username) {
        return orderCrudRepository.findByUsername(username);
    }

}
