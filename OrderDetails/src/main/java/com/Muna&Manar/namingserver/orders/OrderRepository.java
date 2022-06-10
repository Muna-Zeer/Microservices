package com.manar.namingserver.orders;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manar.namingserver.orderDetails.Models.Orders;

import java.util.List;
@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {

    List<Orders> findByCustomerNumber(Long customerNumber);
}
