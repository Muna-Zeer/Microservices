package com.manar.microservices.ProductsLine;

public class ProductLineNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProductLineNotFoundException(String id) {
		super("Could not find productline " + id);
	}
}
