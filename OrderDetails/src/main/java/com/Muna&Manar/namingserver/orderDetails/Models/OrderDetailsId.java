package com.manar.namingserver.orderDetails.Models;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;

@Embeddable
class OrderDetailsId implements Serializable {

   private static final long serialVersionUID = 1L;
   @NotNull
   @ManyToOne
   @JoinColumn(name = "orderNumber", nullable = false)
   private Orders OrderNumber;

   
    @Autowired
	public OrderDetailsId() {
		super();
	}
	@Autowired
	  public OrderDetailsId(@NotNull Orders orderNumber) {
		super();
		OrderNumber = orderNumber; 
	}
	
      @Override

      public boolean equals(Object obj) {

              if (this == obj)

                      return true;

              if (obj == null)

                      return false;

              if (getClass() != obj.getClass())

                      return false;

              OrderDetailsId other = (OrderDetailsId) obj;

              if (OrderNumber != other.OrderNumber)

                      return false;

            

              return true;

      }


}