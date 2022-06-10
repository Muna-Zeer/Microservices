package com.manar.microservices.Products;

public class ProductNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProductNotFoundException(String id) {
		super("Could not find product " + id);
	}
}
