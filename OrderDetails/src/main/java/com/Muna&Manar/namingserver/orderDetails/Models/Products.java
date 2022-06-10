package com.manar.namingserver.orderDetails.Models;

import static javax.persistence.GenerationType.IDENTITY;

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
@Table(name = "products", uniqueConstraints = {
        @UniqueConstraint(columnNames = "productCode")
})
public class Products {
	 @Column(name = "productCode")
	  private String productCode;
	 @Column(name = "productName")
	  private String productName;

	 @Column(name = "productScale")
	  private String productScale;
	 @Column(name = "productVendor")
	  private String productVendor;
	 @Column(name = "productDescription")
	  private String productDescription;
	 @Column(name = "quantityInStock")
	  private Long quantityInStock;
	 @Column(name = "buyPrice")
	  private Double buyPrice;
	 @Column(name = "MSRP")
	  private Double MSRP;


	  @Id
	  @GeneratedValue(strategy = IDENTITY)
	
	  //  @Column(name = "productCode", unique = true, nullable = false, updatable = false)
	  public String getProductCode() {
	    return productCode;
	  }

	  @Autowired
	  public Products() {
		super();
	}

	  @Autowired
	public Products(String productCode, String productName, String productScale,
			String productVendor, String productDescription, Long quantityInStock, Double buyPrice, Double mSRP) {
		super();
		this.productCode = productCode;
		this.productName = productName;
		this.productScale = productScale;
		this.productVendor = productVendor;
		this.productDescription = productDescription;
		this.quantityInStock = quantityInStock;
		this.buyPrice = buyPrice;
		MSRP = mSRP;
	}


	public void setProductCode(String productCode) {
	    this.productCode = productCode;
	  }

	//  @Column(name = "products",nullable = false, length = 70)
	  public String getProductName() {
	    return productName;
	  }

	  public void setProductName(String productName) {
	    this.productName = productName;
	  }

	

	//  @Column(name = "productScale",nullable = false, length = 10)
	  public String getProductScale() {
	    return productScale;
	  }

	  public void setProductScale(String productScale) {
	    this.productScale = productScale;
	  }

	//  @Column(name = "productVendor",nullable = false, length = 50)
	  public String getProductVendor() {
	    return productVendor;
	  }

	  public void setProductVendor(String productVendor) {
	    this.productVendor = productVendor;
	  }

	//  @Column(name = "productDescription",nullable = false, columnDefinition = "text")
	  public String getProductDescription() {
	    return productDescription;
	  }

	  public void setProductDescription(String productDescription) {
	    this.productDescription = productDescription;
	  }

	 // @Column(name = "quantityInStock",nullable = false, columnDefinition = "smallint(6)")
	  public Long getQuantityInStock() {
	    return quantityInStock;
	  }

	  public void setQuantityInStock(Long quantityInStock) {
	    this.quantityInStock = quantityInStock;
	  }

	//  @Column(name = "buyPrice",nullable = false, columnDefinition = "decimal(10,2)")
	  public Double getBuyPrice() {
	    return buyPrice;
	  }

	  public void setBuyPrice(Double buyPrice) {
	    this.buyPrice = buyPrice;
	  }

	//  @Column(name = "MSRP",nullable = false, columnDefinition = "decimal(10,2)")
	  public Double getMSRP() {
	    return MSRP;
	  }

	  public void setMSRP(Double MSRP) {
	    this.MSRP = MSRP;
	  }
}
