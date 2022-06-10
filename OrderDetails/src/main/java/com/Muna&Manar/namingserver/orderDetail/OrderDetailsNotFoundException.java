package com.manar.namingserver.orderDetail;

public class OrderDetailsNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OrderDetailsNotFoundException(Integer id) {
		super("Could not find product " + id);
	}
}
