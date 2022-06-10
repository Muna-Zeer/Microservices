package com.manar.microservices.Model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.beans.factory.annotation.Autowired;



@Entity
@Table(name = "payments", uniqueConstraints = {
        @UniqueConstraint(columnNames = "checkNumber")
})
public class Payments {
	 @Column(name = "checkNumber")
	  private String checkNumber;
	 @Column(name = "amount")
	  private Double amount;
	 @Column(name = "customerNumber")
	  private Customer customerNumber;
	 @Column(name = "paymentDate")
	  private Date paymentDate;
	 
	  @Id
	  @GeneratedValue(strategy = IDENTITY)
	
	  public String getCheckNumber() {
	    return checkNumber;
	  }
	  public void setCheckNumber(String checkNumber) {
			this.checkNumber = checkNumber;
		}

	  @Autowired
	  public Payments() {
		super();
	}

	  @Autowired
	public Payments(String checkNumber, Date paymentDate, Double amount,Customer customerNumber) {
		super();
		this.checkNumber = checkNumber;
		this.paymentDate = paymentDate;
		this.amount = amount;
		this.customerNumber=customerNumber;
		
	}

	 
	public void setCustomerNumber(Customer customerNumber) {
		this.customerNumber = customerNumber;
	}


	//  @Column(name = "amount",nullable = false, columnDefinition = "decimal(10,2)")


	  @ManyToOne
	  @JoinColumn(name = "customerNumber", nullable = false)
	 

	  public Customer getCustomerNumber() {
		return customerNumber;
	}

	
	  public Double getAmount() {
		    return amount;
		  }
	public void setAmount(Double amount) {
	    this.amount = amount;
	  }

	//  @Column(name = "amount",nullable = false, columnDefinition = "decimal(10,2)")
		  public Date getPaymentDate() {
		    return paymentDate;
		  }

		  public void setPaymentDate(Date paymentDate) {
		    this.paymentDate = paymentDate;
		  }
}
