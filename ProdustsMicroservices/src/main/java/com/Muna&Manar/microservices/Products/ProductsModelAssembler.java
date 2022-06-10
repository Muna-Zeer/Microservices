package com.manar.microservices.Products;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;






@Component
public class ProductsModelAssembler implements RepresentationModelAssembler<Products, EntityModel<Products>> {

	  @Override
	  public EntityModel<Products> toModel(Products product) {

	    return EntityModel.of(product, //
	        linkTo(methodOn(ProductResource.class).findById(product.getProductCode())).withSelfRel(),
	        linkTo(methodOn(ProductResource.class).findAll()).withRel("products"));
	  }


}
