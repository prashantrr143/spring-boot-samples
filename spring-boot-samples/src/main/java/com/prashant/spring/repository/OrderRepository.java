package com.prashant.spring.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prashant.spring.common.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Serializable> {

	
	
}
