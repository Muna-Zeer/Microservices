package com.manar.namingserver.orderDetails.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Table(name = "orders", uniqueConstraints = { @UniqueConstraint(columnNames = "orderNumber") })
public class Orders {

	@Column(name = "orderNumber")
	private Long orderNumber;
	@Column(name = "customerNumber")
	private Customer customerNumber;
	@Column(name = "orderDate")
	private Date orderDate;
	@Column(name = "requiredDate")
	private Date requiredDate;
	@Column(name = "shippedDate")
	private Date shippedDate;
	@Column(name = "status")
	private String status;
	@Column(name = "comments")
	private String comments;

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Autowired
	public Orders() {
		super();
	}

	@Autowired
	public Orders(String status, String comments, Date orderDate, Date shippedDate, Date requiredDate, Long orderNumber,
			Customer customerNumber) {
		super();
		this.status = status;
		this.comments = comments;
		this.orderDate = orderDate;
		this.orderNumber = orderNumber;
		this.customerNumber = customerNumber;
		this.shippedDate = shippedDate;
		this.requiredDate = requiredDate;

	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	// @Column(name = "amount",nullable = false, columnDefinition = "decimal(10,2)")

	@ManyToOne
	@JoinColumn(name = "customerNumber", nullable = false)

	public Customer getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(Customer customerNumber) {
		this.customerNumber = customerNumber;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	public Long getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(Long orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Date getRequiredDate() {
		return requiredDate;
	}

	public void setRequiredDate(Date requiredDate) {
		this.requiredDate = requiredDate;
	}

	public Date getShippedDate() {
		return shippedDate;
	}

	public void setShippedDate(Date shippedDate) {
		this.shippedDate = shippedDate;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
}
