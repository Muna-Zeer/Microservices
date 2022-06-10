package com.manar.namingserver.orderDetail;;

public class OrderDetailInfo {
	private int orderNumber;
    private String productCode;
    private int quantityOrdered;
    private Double priceEach;
    private int orderLineNumber;
    
	public OrderDetailInfo() {
		// TODO Auto-generated constructor stub
	}


	
	
	public int getOrderNumber() {
		return orderNumber;
	}




	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}




	public String getProductCode() {
		return productCode;
	}




	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}




	public OrderDetailInfo(int orderNumber, String productCode, int quantityOrdered, Double priceEach,
			int orderLineNumber) {
		super();
		this.orderNumber = orderNumber;
		this.productCode = productCode;
		this.quantityOrdered = quantityOrdered;
		this.priceEach = priceEach;
		this.orderLineNumber = orderLineNumber;
	}




	public int getQuantityOrdered() {
		return quantityOrdered;
	}

	public void setQuantityOrdered(int quantityOrdered) {
		this.quantityOrdered = quantityOrdered;
	}

	public Double getPriceEach() {
		return priceEach;
	}

	public void setPriceEach(Double priceEach) {
		this.priceEach = priceEach;
	}

	public int getOrderLineNumber() {
		return orderLineNumber;
	}

	public void setOrderLineNumber(int orderLineNumber) {
		this.orderLineNumber = orderLineNumber;
	}
	

}
