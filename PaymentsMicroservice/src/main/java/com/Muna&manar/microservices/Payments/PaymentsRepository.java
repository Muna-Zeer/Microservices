package com.manar.microservices.Payments;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manar.microservices.Model.Payments;
 



@Repository
public interface PaymentsRepository extends JpaRepository<Payments, String> {
   // List<Payments> findByPaymentId(Payments PaymentId);
}
