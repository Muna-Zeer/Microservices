package com.manar.microservices.ProductsLine;



import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;




@RestController


public class CategoryResource {




    private ProductLinesRepository repository;
    private final CategoriesModelAssembler assembler;

    public CategoryResource(ProductLinesRepository repository, CategoriesModelAssembler assembler){
        this.assembler = assembler;
        this.repository = repository;
    }

    @GetMapping("/productLines")
    public CollectionModel<EntityModel<ProductLine>> all() {
        List<EntityModel<ProductLine>> products = repository.findAll().stream()
            .map(assembler::toModel)
            .collect(Collectors.toList());
        return CollectionModel.of(products, linkTo(methodOn(CategoryResource.class).all()).withSelfRel());
    }

    @PostMapping("/productLines")
    public ProductLine createNewProduct(@RequestBody ProductLine newProduct) {
        return repository.save(newProduct);
    }

    @GetMapping("/productLines/{productLine}")
    public EntityModel<ProductLine> findById(@PathVariable String id) {

    	ProductLine productLine = repository.findById(id).orElseThrow(() -> new ProductLineNotFoundException(id));
                return assembler.toModel(productLine);
    }

    @PutMapping("/productLines/{productLine}")
	ResponseEntity<?> replaceProductLines(@RequestBody ProductLine newProductLine,
			@PathVariable(value = "productLine") String productLine) {

		ProductLine updatedProductLine =repository.findById(productLine) //
				.map(product -> {
					product.setProductLine(newProductLine.getProductLine());
					product.setHtmlDescription(newProductLine.getHtmlDescription());
					product.setTextDescription(newProductLine.getTextDescription());
					return repository.save(product);
				}) //
				.orElseGet(() -> {
					newProductLine.setProductLine(productLine);
					return repository.save(newProductLine);
				});

		EntityModel<ProductLine> entityModel = assembler.toModel(updatedProductLine);

		return ResponseEntity //
				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
				.body(entityModel);
	}

    @ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/productLines/{productLine}")
	private void delete(@PathVariable String productLine) {
		this.repository.deleteById(productLine);
	}
}