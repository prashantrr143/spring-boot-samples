package com.prashant.spring.rest.controllers;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prashant.spring.common.model.Order;
import com.prashant.spring.repository.OrderRepository;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

	private final Logger logger = LoggerFactory.getLogger(OrderController.class);

	@Autowired
	private OrderRepository orderRepository;

	@GetMapping("/{orderId}/user/{userId}")
	public Set<Order> getAllOrderForUser(@PathVariable("orderId") long orderId, @PathVariable("userId") String userId) {

		return null;
	}

	@GetMapping("/refresh")
	@CacheEvict(value = "orders", allEntries = true)
	public void refreshCache() {

	}

	@GetMapping("/")
	public List<Order> getAllOrders() {
		return orderRepository.findAll();
	}

	@GetMapping("/{orderId}")
	@Cacheable(cacheNames = "orders")
	public Optional<Order> gerOrder(@PathVariable(name = "orderId") long orderid) {
		logger.info("Called method to get order by order id " + orderid);
		return orderRepository.findById(orderid);
	}

}
