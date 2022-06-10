package com.manar.microservices.Payments;

public class PaymentNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PaymentNotFoundException(String id) {
		super("Could not find product " + id);
	}
}
