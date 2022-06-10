package com.manar.namingserver.orders;

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

import com.manar.namingserver.orderDetails.Models.Orders;




@RestController
//@RequestMapping(value = "/orders")
public class OrderResource {

	private OrderRepository orderRepository;
	private final ordersModelAssembler assembler;

	@Autowired
	public OrderResource(OrderRepository orderRepository, ordersModelAssembler assembler) {
		super();
		this.orderRepository = orderRepository;
		this.assembler = assembler;
	}

	@PostMapping("/orders")
	private ResponseEntity<?> newOrders(@RequestBody Orders entity) {

		EntityModel<Orders> entityModel = assembler.toModel(orderRepository.save(entity));

		return ResponseEntity //
				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
				.body(entityModel);
	}

	// update order
	@PutMapping("/orders/{orderNumber}")
	ResponseEntity<?> replaceOrder(@RequestBody Orders newOrder,
			@PathVariable(value = "orderNumber") Long orderNumber) {

		Orders updatedOrder = orderRepository.findById(orderNumber) //
				.map(order1 -> {
					order1.setOrderNumber(newOrder.getOrderNumber());
					order1.setStatus(newOrder.getStatus());
					order1.setOrderDate(newOrder.getOrderDate());

					order1.setRequiredDate(newOrder.getRequiredDate());
					//order1.setCustomerNumber(newOrder.getCustomerNumber());
					return orderRepository.save(order1);
				}) //

				.orElseGet(() -> {
					newOrder.setOrderNumber(orderNumber);
					return orderRepository.save(newOrder);
				});

		EntityModel<Orders> entityModel = assembler.toModel(updatedOrder);

		return ResponseEntity //
				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
				.body(entityModel);
	}


	// findById
	@GetMapping(value = "/orders/{orderNumber}")

	public EntityModel<Orders> findById(@PathVariable long orderNumber) {

		Orders order = orderRepository.findById(orderNumber) //
				.orElseThrow(() -> new OrderNotFoundException(orderNumber));

		return EntityModel.of(order, //
				linkTo(methodOn(OrderResource.class).findById(order.getOrderNumber())).withSelfRel(),
				linkTo(methodOn(OrderResource.class).findAll()).withRel("orders"));
	}

/*	@GetMapping
	public ResponseEntity<CollectionModel<EntityModel<Orders>>> findAll() {
		return ResponseEntity.ok(assembler.toCollectionModel(orderRepository.findAll()));

	}
*/
	@GetMapping("/orders")
	public CollectionModel<EntityModel<Orders>> findAll() {

	  List<EntityModel<Orders>> orders = orderRepository.findAll().stream()
	      .map(order-> EntityModel.of(order,
	          linkTo(methodOn(OrderResource.class).findById(order.getOrderNumber())).withSelfRel(),
	          linkTo(methodOn(OrderResource.class).findAll()).withRel("orders")))
	      .collect(Collectors.toList());

	  return CollectionModel.of(orders , linkTo(methodOn(OrderResource.class).findAll()).withSelfRel());
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping(value = "/orders/{orderNumber}")
	private void delete(@PathVariable Long orderNumber) {
		this.orderRepository.deleteById(orderNumber);
	}
}