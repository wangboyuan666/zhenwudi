package com.jt.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.mapper.OrderItemMapper;
import com.jt.mapper.OrderMapper;
import com.jt.mapper.OrderShippingMapper;
import com.jt.pojo.Order;
import com.jt.pojo.OrderItem;
import com.jt.pojo.OrderShipping;

@Service
public class DubboOrderServiceImpl implements DubboOrderService{
	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private OrderItemMapper orderItemMapper;
	@Autowired
	private OrderShippingMapper orderShippingMapper;
	@Transactional(rollbackFor = Throwable.class)
	@Override
	public String saveOrder(Order order) {
		String uuid = UUID.randomUUID().toString().replace("-", "");
		//1.入库订单
		Date date = new Date();
		order.setOrderId(uuid).setStatus(1).setCreated(date).setUpdated(date);
		orderMapper.insert(order);
		//入库订单物流
		OrderShipping shipping = order.getOrderShipping();
		shipping.setOrderId(uuid).setCreated(date).setUpdated(date);
		orderShippingMapper.insert(shipping);
		//入库订单商品
		List<OrderItem> orderItems = order.getOrderItems();
		for(OrderItem orderItem : orderItems) {
			orderItem.setOrderId(uuid).setCreated(date).setUpdated(date);
			orderItemMapper.insert(orderItem);
		}
		return uuid;
	}
	/**
	 * 根据ordereId查询数据库
	 * @param userId
	 * @return
	 */
	@Override
	public Order findOrderById(String id) {
		Order order = orderMapper.selectOrderById(id);
		return order;
	}
	
}
