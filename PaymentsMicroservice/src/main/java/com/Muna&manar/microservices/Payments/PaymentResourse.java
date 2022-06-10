package com.manar.microservices.Payments;



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

import com.manar.microservices.Model.Payments;


@RestController
//@RequestMapping(value = "/payments")
public class PaymentResourse {

	private PaymentsRepository paymentsRepository;
	private final PaymentsModelAssembler assembler;

	@Autowired
	public PaymentResourse(PaymentsRepository paymentsRepository, PaymentsModelAssembler assembler) {
		super();
		this.paymentsRepository = paymentsRepository;
		this.assembler = assembler;
	}

	@PostMapping("/payments")
	private ResponseEntity<?> newPayment( @RequestBody Payments entity) {

		EntityModel<Payments> entityModel = assembler.toModel(paymentsRepository.save(entity));

		return ResponseEntity //
				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
				.body(entityModel);
	}

	// update product

	@PutMapping("/payments/{checkNumber}")
	ResponseEntity<?> replacePayments(@RequestBody Payments newPayment,
			@PathVariable(value = "checkNumber") String checkNumber) {

		Payments updatedPayment = paymentsRepository.findById(checkNumber) //
				.map(payment1 -> {
					payment1.setCheckNumber(newPayment.getCheckNumber());
					payment1.setAmount(newPayment.getAmount());
					//payment1.setCustomerNumber(newPayment.getCustomerNumber());
					payment1.setPaymentDate(newPayment.getPaymentDate());
					//payment1.setCustomerNumber(newPayment.getCustomerNumber());

					return paymentsRepository.save(payment1);
				}) //

				.orElseGet(() -> {
					newPayment.setCheckNumber(checkNumber);
					return paymentsRepository.save(newPayment);
				});

		EntityModel<Payments> entityModel = assembler.toModel(updatedPayment);

		return ResponseEntity //
				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
				.body(entityModel);
	}

	
	//@GetMapping(value = "/{checkNumber}")
/*	public ResponseEntity<EntityModel<Payments>> findById(@PathVariable String checkNumber) {

		return paymentsRepository.findById(checkNumber) //
				.map(assembler::toModel) //
				.map(ResponseEntity::ok) //
				.orElse(ResponseEntity.notFound().build());
	}*/

	@GetMapping(value = "/payments/{checkNumber}")
	public EntityModel<Payments> findById(@PathVariable String checkNumber) {

		Payments payment = paymentsRepository.findById(checkNumber) //
				.orElseThrow(() -> new PaymentNotFoundException(checkNumber));

		return EntityModel.of(payment, //
				linkTo(methodOn(PaymentResourse.class).findById(payment.getCheckNumber())).withSelfRel(),
				linkTo(methodOn(PaymentResourse.class).findAll()).withRel("payments"));
	}
	/*@GetMapping
	public ResponseEntity<CollectionModel<EntityModel<Payments>>> findAll() {
		return ResponseEntity.ok(assembler.toCollectionModel(paymentsRepository.findAll()));

	}*/
	@GetMapping( "/payments")
	public	CollectionModel<EntityModel<Payments>> findAll() {

	  List<EntityModel<Payments>> payments = paymentsRepository.findAll().stream()
	      .map(payment-> EntityModel.of(payment,
	          linkTo(methodOn(PaymentResourse.class).findById(payment.getCheckNumber())).withSelfRel(),
	          linkTo(methodOn(PaymentResourse.class).findAll()).withRel("payments")))
	      .collect(Collectors.toList());

	  return CollectionModel.of(payments , linkTo(methodOn(PaymentResourse.class).findAll()).withSelfRel());
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/payments/{checkNumber}")
	private void delete(@PathVariable String checkNumber) {
		this.paymentsRepository.deleteById(checkNumber);
	}
}
