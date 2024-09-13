package com.ead.payment_microservice.repository;



import com.ead.payment_microservice.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {


    Optional<Payment> findByName(String name);
}

