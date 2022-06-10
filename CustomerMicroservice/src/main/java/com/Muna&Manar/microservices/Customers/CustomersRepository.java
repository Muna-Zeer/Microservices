package com.manar.microservices.Customers;

import org.springframework.data.jpa.repository.JpaRepository;



public interface CustomersRepository extends JpaRepository<Customer, Integer> {
}