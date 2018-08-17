package com.prashant.spring.common.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "items")
public class Item extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ITEM_ID")
	@SequenceGenerator(name = "item_id_sequence", sequenceName = "item_id_sequence")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_id_sequence")
	private long id;

	@Column(name = "ITEM_NAME")
	private String name;

	@Column(name = "ITEM_AMOUNT")
	private BigDecimal amount;

	//@JsonManagedReference
	@JsonIgnore
	@ManyToOne
	private Order order;

	
	
	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", amount=" + amount + "]";
	}

}
