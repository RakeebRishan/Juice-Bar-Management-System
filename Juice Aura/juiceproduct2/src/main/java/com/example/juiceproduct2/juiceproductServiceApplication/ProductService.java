package com.example.juiceproduct2.juiceproductServiceApplication;


import com.example.juiceproduct2.juiceproductEntity.Product;
import com.example.juiceproduct2.juiceproductRepository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // Get all products
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Get a product by ID
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }


    // Add a new product
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    // Update an existing product
    public Product updateProduct(Long id, Product product) {
        // Check if the product exists
        if (productRepository.existsById(id)) {
            product.setId(id);
            return productRepository.save(product);
        }
        // Return null or handle as appropriate if not found
        return null;
    }
    // Delete a product by ID
    public boolean deleteProduct(Long id) {
        // Check if the product exists
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true; // Return true to indicate successful deletion
        }
        // Return false or handle as appropriate if not found
        return false;
    }
}
