package com.example.juiceproduct2.juiceproductRepository;


import com.example.juiceproduct2.juiceproductEntity.Product;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}

