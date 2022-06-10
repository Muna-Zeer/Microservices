package com.manar.microservices.Payments;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.manar.microservices.Model.Payments;

@Component
public class PaymentsModelAssembler implements RepresentationModelAssembler<Payments, EntityModel<Payments>> {

	  @Override
	  public EntityModel<Payments> toModel(Payments payment) {

	    return EntityModel.of(payment, //
	        linkTo(methodOn(PaymentResourse.class).findById(payment.getCheckNumber())).withSelfRel(),
	        linkTo(methodOn(PaymentResourse.class).findAll()).withRel("payments"));
	  }
}
