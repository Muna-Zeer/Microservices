package com.manar.microservices.Customers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;




@RestController
public class CustomerController {
    private CustomersRepository repository;
    private final CustomerModelAssembler assembler;

    public CustomerController(CustomersRepository repository, CustomerModelAssembler assembler){
        this.assembler = assembler;
        this.repository = repository;
    }

    @GetMapping("/customers")
    public CollectionModel<EntityModel<Customer>> getAllCustomers() {
        List<EntityModel<Customer>> customers = repository.findAll().stream()
            .map(assembler::toModel)
            .collect(Collectors.toList());
        return CollectionModel.of(customers, linkTo(methodOn(CustomerController.class).getAllCustomers()).withSelfRel());
    }

    @PostMapping("/customers")
    public Customer createNewCustomer(@RequestBody Customer newCustomer) {
        return repository.save(newCustomer);
    }

    @GetMapping("/customers/{customerNumber}")
    public EntityModel<Customer> getCustomerById(@PathVariable Integer id) {

        Customer customer = repository.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));
                return assembler.toModel(customer);
    }

    @PutMapping("/customers/{customerNumber}")
    public Customer updateCustomer(@RequestBody Customer newCustomer, @PathVariable Integer id) {

        return repository.findById(id)
                .map(customer -> {
                    customer.setCustomerNumber(newCustomer.getCustomerNumber());
                    customer.setName(newCustomer.getName());
                    customer.setContactLastName(newCustomer.getContactLastName());
                    customer.setPhone(newCustomer.getPhone());
                    customer.setAddressLine1(newCustomer.getAddressLine1());
                    customer.setAddressLine2(newCustomer.getAddressLine2());
                    customer.setCity(newCustomer.getCity());
                    customer.setState(newCustomer.getState());
                    customer.setPostalCode(newCustomer.getPostalCode());
                    customer.setCountry(newCustomer.getCountry());
                    customer.setCreditLimit(newCustomer.getCreditLimit());
                    return repository.save(customer);
                }).orElseGet(() -> {
                    newCustomer.setCustomerNumber(id);
                    return repository.save(newCustomer);
                });
    }

    @DeleteMapping("/customers/{customerNumber}")
    public void deleteCustomer(@PathVariable Integer id) {
    	if(repository.existsById(id)) 
        repository.deleteById(id);
    	else throw new CustomerNotFoundException(id);
    }
}
