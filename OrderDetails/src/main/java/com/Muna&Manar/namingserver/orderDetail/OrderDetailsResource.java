package com.manar.namingserver.orderDetail;



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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.manar.namingserver.orderDetails.Models.OrderDetails;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
//@RequestMapping(value = "/payments")
public class OrderDetailsResource {

	private OrderDetailsRepository orderDetailsRepository;
	private final OrderDetailsAssembler assembler;

	@Autowired
	public OrderDetailsResource(OrderDetailsRepository orderDetailsRepository, OrderDetailsAssembler assembler) {
		super();
		this.orderDetailsRepository = orderDetailsRepository;
		this.assembler = assembler;
	}



	
	@GetMapping("/orderDetails")
	public ResponseEntity<CollectionModel<EntityModel<OrderDetails>>> findAll() {
		return ResponseEntity.ok(assembler.toCollectionModel(orderDetailsRepository.findAll()));

	}
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/orderDetails/{orderNumber}")
	public ResponseEntity<Long> deleteOrder(@PathVariable(value = "orderNumber") Long orderId) {
	    // Access the DB and delete the order
	    return ResponseEntity.ok(orderId);
	}

	
}
