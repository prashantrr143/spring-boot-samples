package com.prashant.spring.common.model;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "orders")
public class Order extends BaseEntity{

	//private final Logger logger = LoggerFactory.getLogger(Order.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "order_id_sequence", sequenceName = "order_id_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_id_sequence")
	private long orderId;

	@Column(name = "name")
	private String name;

	@Column(name = "total_amount")
	private BigDecimal amount;

	//@JsonBackReference
	//@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "order", cascade = CascadeType.ALL)
	private Set<Item> orderItems = new HashSet<>();

	public void addItem(Item item) {
	//	logger.info(" Adding item to this order  :" + item.getId() + "   name: " + item.getName());
		this.getOrderItems().add(item);
		item.setOrder(this);
		updateOrderAmount();
	}

	private void updateOrderAmount() {
		Iterator<Item> it = orderItems.iterator();
		BigDecimal amount = new BigDecimal("0.0");
		while (it.hasNext()) {

			amount = amount.add(it.next().getAmount());
		}

		this.setAmount(amount);
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Set<Item> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(Set<Item> orderItems) {
		this.orderItems = orderItems;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", name=" + name + ", amount=" + amount + ", orderItems=" + orderItems
				+ "]";
	}

}
