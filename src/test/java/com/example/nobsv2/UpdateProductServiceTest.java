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
import org.springframework.http.ResponseEntity;

import com.example.nobsv2.exceptions.ProductNotFoundException;
import com.example.nobsv2.product.ProductRepository;
import com.example.nobsv2.product.model.Product;
import com.example.nobsv2.product.model.ProductDTO;
import com.example.nobsv2.product.model.UpdateProductCommand;
import com.example.nobsv2.product.services.UpdateProdcutService;

public class UpdateProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private UpdateProdcutService updateProdcutService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void given_product_exists_when_update_service_then_return_product_dto() {
        // Given 
        Product product = new Product();
        product.setId(1);
        product.setName("Product Name");
        product.setDescription("Some description that is more than 20 chars");
        product.setPrice(9.99);

        when(productRepository.findById(1)).thenReturn(Optional.of(product));

        // When
        ResponseEntity<ProductDTO> response = updateProdcutService.execute(new UpdateProductCommand(1, product));

        // Then
        assertEquals(ResponseEntity.ok(new ProductDTO(product)), response);
        verify(productRepository, times(1)).findById(1);
    }

    @Test
    public void given_product_doesnt_exist_when_product_update_service_throw_product_not_found_exception() {
        // Given
        when(productRepository.findById(1)).thenReturn(Optional.empty());

        // When & Then
        UpdateProductCommand updateProduct = new UpdateProductCommand(1, new Product());
        assertThrows(ProductNotFoundException.class, () -> updateProdcutService.execute(updateProduct));
        verify(productRepository, times(1)).findById(1);
    }

}
