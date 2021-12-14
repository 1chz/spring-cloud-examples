package io.github.shirohoo.ecommerce.order.adapter.persistence;

import io.github.shirohoo.ecommerce.order.domain.Order;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderCrudRepositoryImpl implements OrderCrudRepository {

    private final OrderJpaRepository jpaRepository;

    @Override
    public Order save(Order order) {
        OrderEntity savedOrder = jpaRepository.save(OrderEntity.convert(order));
        return savedOrder.toOrder();
    }

    @Override
    public Order findByOrderId(String orderId) {
        return jpaRepository.findByOrderId(orderId)
            .map(OrderEntity::toOrder)
            .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<Order> findByUsername(String username) {
        return jpaRepository.findByUsername(username)
            .stream()
            .map(OrderEntity::toOrder)
            .collect(Collectors.toUnmodifiableList());
    }

}
