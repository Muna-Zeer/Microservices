package com.manar.namingserver.orders;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.manar.namingserver.orderDetails.Models.Orders;






@Component
public class ordersModelAssembler implements RepresentationModelAssembler<Orders, EntityModel<Orders>> {

	  @Override
	  public EntityModel<Orders> toModel(Orders order) {

	    return EntityModel.of(order, //
	        linkTo(methodOn(OrderResource.class).findById(order.getOrderNumber())).withSelfRel(),
	        linkTo(methodOn(OrderResource.class).findAll()).withRel("orders"));
	  }

}
