package com.prashant.spring.common.model;

import java.util.Set;

public class User extends BaseEntity {

	private String userId;
	private String fName;
	private String lName;

	private Set<Order> placedOrders;

	private Set<Order> currentCartOrders;

	private Address billingAddress;
	private Address shippingAddress;

	private Set<Address> addressess;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public Set<Order> getPlacedOrders() {
		return placedOrders;
	}

	public void setPlacedOrders(Set<Order> placedOrders) {
		this.placedOrders = placedOrders;
	}

	public Set<Order> getCurrentCartOrders() {
		return currentCartOrders;
	}

	public void setCurrentCartOrders(Set<Order> currentCartOrders) {
		this.currentCartOrders = currentCartOrders;
	}

	public Address getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(Address billingAddress) {
		this.billingAddress = billingAddress;
	}

	public Address getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(Address shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public Set<Address> getAddressess() {
		return addressess;
	}

	public void setAddressess(Set<Address> addressess) {
		this.addressess = addressess;
	}

}
