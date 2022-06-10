package com.manar.namingserver.orderDetails.Models;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;

@Entity
/*@Table(name = "orderDetails", uniqueConstraints = {
        @UniqueConstraint(columnNames = "orderNumber"),
        @UniqueConstraint(columnNames = "productCode")
})*/
@Table(name = "orderdetails")
public class OrderDetails {

    @EmbeddedId
    private OrderDetailsId id;

    @NotBlank
    @Column(nullable = false, length = 11)
    private Integer quantityOrdered;

    @NotBlank
    @Column(nullable = false)
    private BigDecimal priceEach;

    @NotBlank
    @Column(nullable = false, length = 6)
    private Integer orderLineNumber;
    @Autowired
	public OrderDetails(@NotBlank Integer quantityOrdered, @NotBlank BigDecimal priceEach,
			@NotBlank Integer orderLineNumber) {
		super();
		this.quantityOrdered = quantityOrdered;
		this.priceEach = priceEach;
		this.orderLineNumber = orderLineNumber;
	}

	@Autowired
	public OrderDetails(OrderDetailsId id, @NotBlank Integer quantityOrdered, @NotBlank BigDecimal priceEach,
			@NotBlank Integer orderLineNumber) {
		super();
		this.id = id;
		this.quantityOrdered = quantityOrdered;
		this.priceEach = priceEach;
		this.orderLineNumber = orderLineNumber;
	}

	@Autowired
	public OrderDetails() {
		super();
	}


	public OrderDetailsId getId() {
		return id;
	}

	public void setId(OrderDetailsId id) {
		this.id = id;
	}

	public Integer getQuantityOrdered() {
		return quantityOrdered;
	}

	public void setQuantityOrdered(Integer quantityOrdered) {
		this.quantityOrdered = quantityOrdered;
	}

	public BigDecimal getPriceEach() {
		return priceEach;
	}

	public void setPriceEach(BigDecimal priceEach) {
		this.priceEach = priceEach;
	}

	public Integer getOrderLineNumber() {
		return orderLineNumber;
	}

	public void setOrderLineNumber(Integer orderLineNumber) {
		this.orderLineNumber = orderLineNumber;
	}
    

}
