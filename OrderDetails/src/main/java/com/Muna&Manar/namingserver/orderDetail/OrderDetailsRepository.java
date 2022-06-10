package com.manar.namingserver.orderDetail;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manar.namingserver.orderDetails.Models.OrderDetails;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Integer> {
	//List<OrderDetails> findAllByOrder(Orders orderNumber); 
	//List<OrderDetails> findAllByProduct(Products productCode); 

}