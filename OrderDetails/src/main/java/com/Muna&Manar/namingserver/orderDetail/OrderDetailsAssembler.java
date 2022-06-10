package com.manar.namingserver.orderDetail;
import org.springframework.stereotype.Component;

import com.manar.namingserver.orderDetails.Models.OrderDetails;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

@Component
public class OrderDetailsAssembler implements RepresentationModelAssembler<OrderDetails, EntityModel<OrderDetails>> {

	  @Override
	  public EntityModel<OrderDetails> toModel(OrderDetails order) {

	    return EntityModel.of(order, //
	     
	        linkTo(methodOn(OrderDetailsResource.class).findAll()).withRel("orderDetails"));
	  }

}
