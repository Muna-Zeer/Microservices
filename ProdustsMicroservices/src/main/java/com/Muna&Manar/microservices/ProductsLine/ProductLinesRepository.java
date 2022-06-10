package com.manar.microservices.ProductsLine;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface ProductLinesRepository extends JpaRepository<ProductLine, String>{
	
}
