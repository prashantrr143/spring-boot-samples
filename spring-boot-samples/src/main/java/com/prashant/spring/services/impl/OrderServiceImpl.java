package com.prashant.spring.services.impl;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.prashant.spring.common.exceptions.OrderNotFoundException;
import com.prashant.spring.common.model.Order;
import com.prashant.spring.services.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Override
	public Set<Order> getAllOrdersForUser(String userId) throws OrderNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order getOrderbyId(long orderId) throws OrderNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order getOrderForUser(String userId, long orderId) throws OrderNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
