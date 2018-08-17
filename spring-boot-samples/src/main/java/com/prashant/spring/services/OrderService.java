package com.prashant.spring.services;

import java.util.Set;

import com.prashant.spring.common.exceptions.OrderNotFoundException;
import com.prashant.spring.common.model.Order;

/**
 *  Order Service contract
 * @author prasingh26
 *
 */
public interface OrderService {
	/**
	 * Method to get Orders for a given user
	 * 
	 * @param userId User Id of the User
	 * @return Set<Order> set of orders for the user id passed
	 * @throws OrderNotFoundException if no orders are found
	 */
	Set<Order> getAllOrdersForUser(String userId) throws OrderNotFoundException;

	/**
	 * Method to fetch the Order details for a given order id
	 * 
	 * @param orderId Order id
	 * @return
	 * @throws OrderNotFoundException if no order found for Order Id passed
	 */
	Order getOrderbyId(long orderId) throws OrderNotFoundException;

	/**
	 * Method to get a specific order for a user
	 * 
	 * @param userId  User Id
	 * @param orderId Order Id
	 * @return
	 * @throws OrderNotFoundException If no order found for passed parameters
	 */
	Order getOrderForUser(String userId, long orderId) throws OrderNotFoundException;
}
