
package com.manar.microservices.Customers;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


@Component
public class CustomerModelAssembler implements RepresentationModelAssembler<Customer, EntityModel<Customer>> {
	@Override
	public EntityModel<Customer> toModel(Customer customer) {
		return EntityModel.of(customer,
				linkTo(methodOn(CustomerController.class).getCustomerById(customer.getCustomerNumber())).withSelfRel(),
				linkTo(methodOn(CustomerController.class).getAllCustomers()).withRel("customers"));
	}
}
