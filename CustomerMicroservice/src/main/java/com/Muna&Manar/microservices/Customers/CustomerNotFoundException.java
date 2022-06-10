package com.manar.microservices.Customers;

public class CustomerNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomerNotFoundException(Integer id) {
		super("Could not find customer " + id);
	}
}
