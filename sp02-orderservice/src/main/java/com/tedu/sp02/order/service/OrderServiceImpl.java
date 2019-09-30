package com.tedu.sp02.order.service;

import com.tedu.sp01.pojo.Order;
import com.tedu.sp01.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {
    @Override
    public Order getOrder(String orderId) {
        Order order = new Order();
        order.setId(orderId);
        return order;
    }

    @Override
    public void addOrder(Order order) {
        log.info("保存订单："+order);
    }
}
