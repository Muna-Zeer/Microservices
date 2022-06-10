package com.manar.microservices.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customers")
public class Customer {
	//public @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Integer id;
	 @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	 public Integer customerNumber;
	 @Column(name = "customerName")
	private String name;
	 @Column(name = "contactLastName")
	private String contactLastName;
	 @Column(name = "phone")
	private String phone;
	 @Column(name = "addressLine1")
	private String addressLine1;
	 @Column(name = "addressLine2")
	private String addressLine2;
	 @Column(name = "city")
	private String city;
	 @Column(name = "state")
	private String state;
	 @Column(name = "postalCode")
	private String postalCode;
	 @Column(name = "country")
	private String country;
	 @Column(name = "creditLimit")
	// private Integer salesEmployeeNumber TODO: Add employees later
	private Double creditLimit;

	public Customer(String name, String contactLastName, String phone, String addressLine1, String addressLine2,
			String city, String state, String postalCode, String country, Double creditLimit) {
		super();
		this.name = name;
		this.contactLastName = contactLastName;
		this.phone = phone;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.city = city;
		this.state = state;
		this.postalCode = postalCode;
		this.country = country;
		this.creditLimit = creditLimit;
	}

	public Customer() {
	}

	/*public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
*/
	
	public String getName() {
		return name;
	}

	public Integer getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(Integer customerNumber) {
		this.customerNumber = customerNumber;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContactLastName() {
		return contactLastName;
	}

	public void setContactLastName(String contactLastName) {
		this.contactLastName = contactLastName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Double getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(Double creditLimit) {
		this.creditLimit = creditLimit;
	}

}
