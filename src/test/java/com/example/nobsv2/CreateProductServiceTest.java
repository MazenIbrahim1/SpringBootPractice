package com.example.nobsv2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.nobsv2.product.ProductRepository;
import com.example.nobsv2.product.model.Product;
import com.example.nobsv2.product.model.ProductDTO;
import com.example.nobsv2.product.services.CreateProductService;

public class CreateProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private CreateProductService createProductService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @SuppressWarnings("null")
    @Test
    public void given_valid_product_when_create_product_service_return_product_dto() {
        // Given
        Product product = new Product();
        product.setId(1);
        product.setName("Product Name");
        product.setDescription("Some description that is more than 20 chars");
        product.setPrice(9.99);

        when(productRepository.save(product)).thenReturn(product);

        // When
        ResponseEntity<ProductDTO> response = createProductService.execute(product);

        // Then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(product.getId(), response.getBody().getId());
        assertEquals(product.getDescription(), response.getBody().getDescription());
        assertEquals(product.getName(), response.getBody().getName());
        verify(productRepository, times(1)).save(product);
    }
}
