package com.ead.payment_microservice.repository;



import com.ead.payment_microservice.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {


    List<Item> findByPaymentId(Long paymentId);
}
