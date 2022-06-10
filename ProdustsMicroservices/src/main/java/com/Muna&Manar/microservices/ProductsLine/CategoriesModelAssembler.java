package com.manar.microservices.ProductsLine;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;





@Component
public class CategoriesModelAssembler implements RepresentationModelAssembler<ProductLine, EntityModel<ProductLine>> {

	  @Override
	  public EntityModel<ProductLine> toModel(ProductLine category) {

	    return EntityModel.of(category, //
	        linkTo(methodOn(CategoryResource.class).findById(category.getProductLine())).withSelfRel(),
	        linkTo(methodOn(CategoryResource.class).all()).withRel("productlines"));
	  }
}
