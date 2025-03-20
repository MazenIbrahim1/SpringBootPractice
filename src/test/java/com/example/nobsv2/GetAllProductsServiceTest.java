package com.example.nobsv2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.example.nobsv2.product.ProductRepository;
import com.example.nobsv2.product.model.Product;
import com.example.nobsv2.product.model.ProductDTO;
import com.example.nobsv2.product.services.GetProductsService;

public class GetAllProductsServiceTest {
    // TO IMPLEMENT

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private  GetProductsService getProductsService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void given_product_when_get_products_return_list_product_dtos() {
        // Given
        Product product = new Product();
        product.setId(1);
        product.setName("Product Name");
        product.setDescription("Some description that is more than 20 chars");
        product.setPrice(9.99);

        when(productRepository.findAll()).thenReturn(Arrays.asList(product));

        // When
        ResponseEntity<List<ProductDTO>> productDTOs = getProductsService.execute(null);

        // Then
        assertEquals(ResponseEntity.ok(Arrays.asList(productDTOs)), productDTOs);
        verify(productRepository, times(1)).findAll();
    }
}
