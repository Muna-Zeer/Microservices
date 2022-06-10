package com.manar.microservices.Products;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ProductsRepository extends JpaRepository<Products, String>{

    List<Products> findByProductLine(String productLine);
}