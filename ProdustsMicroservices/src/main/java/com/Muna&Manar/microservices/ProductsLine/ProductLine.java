package com.manar.microservices.ProductsLine;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Table(name = "productLines", uniqueConstraints = {
        @UniqueConstraint(columnNames = "productLine")
})

public class ProductLine {
	@Column( name = "productLine")
	  private String productLine;
	@Column(name = "textDescription")
	  private String textDescription;
	@Column(name = "htmlDescription")
	  private String htmlDescription;
	@Column(name = "image")
	  private byte[] image;

	  @Id
	 // @Column( name = "productLine",unique = true, nullable = false, length = 50)
	  public String getProductLine() {
	    return productLine;
	  }
	  
	

	@Autowired
	  public ProductLine() {
		super();
	}

	  @Autowired
	public ProductLine(String productLine, String textDescription, String htmlDescription, byte[] image) {
		super();
		this.productLine = productLine;
		this.textDescription = textDescription;
		this.htmlDescription = htmlDescription;
		this.image = image;
	}

	public void setProductLine(String productLine) {
	    this.productLine = productLine;
	  }

	  //@Column(name = "textDescription",length = 4000)
	  public String getTextDescription() {
	    return textDescription;
	  }

	  public void setTextDescription(String textDescription) {
	    this.textDescription = textDescription;
	  }

	//  @Column(name = "htmlDescription",columnDefinition="mediumtext")
	  public String getHtmlDescription() {
	    return htmlDescription;
	  }

	  public void setHtmlDescription(String htmlDescription) {
	    this.htmlDescription = htmlDescription;
	  }

	  @Lob
	  //@Column(name = "image",columnDefinition="mediumblob")
	  public byte[] getImage() {
	    return image;
	  }

	  public void setImage(byte[] image) {
	    this.image = image;
	  }
}
