package com.example.nobsv2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.nobsv2.exceptions.ProductNotFoundException;
import com.example.nobsv2.product.ProductRepository;
import com.example.nobsv2.product.model.Product;
import com.example.nobsv2.product.services.DeleteProductService;

public class DeleteProductServiceTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private DeleteProductService deleteProductService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void given_product_id_exists_when_delete_service_product_no_longer_exists() {
        // Given
        Product product = new Product();
        product.setId(1);
        product.setName("Product Name");
        product.setDescription("Some description that is more than 20 chars");
        product.setPrice(9.99);

        when(productRepository.findById(1)).thenReturn(Optional.of(product));

        // When
        ResponseEntity<Void> response = deleteProductService.execute(1);

        // Then
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(productRepository, times(1)).findById(1);
        verify(productRepository, times(1)).delete(product);
    }

    @Test
    public void given_product_doesnt_exist_when_product_delete_service_throw_product_not_found_exception() {
        // Given
        when(productRepository.findById(1)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(ProductNotFoundException.class, () -> deleteProductService.execute(1));
        verify(productRepository, times(1)).findById(1);
    }   
}
