package com.manar.microservices.Products;


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

@RestController
public class ProductResource {

	private ProductsRepository productsRepository;
	private final ProductsModelAssembler assembler;

	@Autowired
	public ProductResource(ProductsRepository productsRepository, ProductsModelAssembler assembler) {
		super();
		this.productsRepository = productsRepository;
		this.assembler = assembler;
	}

	@PostMapping("/products")
	private ResponseEntity<?> newProduct( @RequestBody Products entity) {

		EntityModel<Products> entityModel = assembler.toModel(productsRepository.save(entity));

		return ResponseEntity //
				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
				.body(entityModel);
	}

	
	// update product
	
	@PutMapping("/products/{productCode}")
	ResponseEntity<?> replaceProducts(@RequestBody Products newProduct,
			@PathVariable(value = "productCode") String productCode) {

		Products updatedProduct = productsRepository.findById(productCode) //
				.map(product1 -> {
					product1.setProductCode(newProduct.getProductCode());
					product1.setProductName(newProduct.getProductName());
					product1.setBuyPrice(newProduct.getBuyPrice());
					product1.setProductLine(newProduct.getProductLine());

						return productsRepository.save(product1);
				}) //
				.orElseGet(() -> {
					newProduct.setProductCode(productCode);
					return productsRepository.save(newProduct);
				});

		EntityModel<Products> entityModel = assembler.toModel(updatedProduct);

		return ResponseEntity //
				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
				.body(entityModel);
	}

/*	@GetMapping(value = "/{productCode}")
	public ResponseEntity<EntityModel<Products>> findById(@PathVariable String productCode) {

		return productsRepository.findById(productCode) //
				.map(assembler::toModel) //
				.map(ResponseEntity::ok) //
				.orElse(ResponseEntity.notFound().build());
	}*/
	@GetMapping("/products/{productCode}")
	public EntityModel<Products> findById(@PathVariable String productCode) {

		Products product = productsRepository.findById(productCode) //
				.orElseThrow(() -> new ProductNotFoundException(productCode));

		return EntityModel.of(product, //
				linkTo(methodOn(ProductResource.class).findById(product.getProductCode())).withSelfRel(),
				linkTo(methodOn(ProductResource.class).findAll()).withRel("products"));
	}

/*	@GetMapping
	public ResponseEntity<CollectionModel<EntityModel<Products>>> findAll() {
		return ResponseEntity.ok(assembler.toCollectionModel(productsRepository.findAll()));

	}
*/
	@GetMapping("/products")
	public	CollectionModel<EntityModel<Products>> findAll() {

	  List<EntityModel<Products>> products = productsRepository.findAll().stream()
	      .map(product-> EntityModel.of(product,
	          linkTo(methodOn(ProductResource.class).findById(product.getProductCode())).withSelfRel(),
	          linkTo(methodOn(ProductResource.class).findAll()).withRel("products")))
	      .collect(Collectors.toList());

	  return CollectionModel.of(products , linkTo(methodOn(ProductResource.class).findAll()).withSelfRel());
	}
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/products/{productCode}")
	private void delete(@PathVariable String productCode) {
		this.productsRepository.deleteById(productCode);
	}

}
  