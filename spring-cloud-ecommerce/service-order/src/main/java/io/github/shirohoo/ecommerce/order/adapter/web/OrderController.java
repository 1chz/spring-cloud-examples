package io.github.shirohoo.ecommerce.order.adapter.web;

import io.github.shirohoo.ecommerce.order.service.OrderCrudService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderCrudService orderCrudService;

}
