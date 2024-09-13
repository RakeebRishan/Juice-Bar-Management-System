package com.example.juiceproduct2.juiceproductController;


import com.example.juiceproduct2.juiceproductEntity.Product;
import com.example.juiceproduct2.juiceproductServiceApplication.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    public ProductControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetProductById_Success() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Test Product");
        product.setQuantity(10);

        when(productService.getProductById(1L)).thenReturn(product);

        ResponseEntity<Product> response = productController.getProductById(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(product, response.getBody());
    }

    @Test
    public void testGetProductById_NotFound() {
        when(productService.getProductById(1L)).thenReturn(null);

        ResponseEntity<Product> response = productController.getProductById(1L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }


}
