package com.manar.namingserver.orders;

public class OrderNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OrderNotFoundException(Long id) {
		super("Could not find product " + id);
	}
}
