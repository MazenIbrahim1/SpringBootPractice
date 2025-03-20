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

    @SuppressWarnings("null")
    @Test
    public void given_product_when_get_products_return_list_product_dtos() {
        // Given
        Product product1 = new Product();
        product1.setId(1);
        product1.setName("Product Name");
        product1.setDescription("Some description that is more than 20 chars");
        product1.setPrice(9.99);

        Product product2 = new Product();
        product2.setId(2);
        product2.setName("Product 2");
        product2.setDescription("Description 2");
        product2.setPrice(15.99);

        when(productRepository.findAll()).thenReturn(Arrays.asList(product1, product2));

        // When
        ResponseEntity<List<ProductDTO>> result = getProductsService.execute(null);

        // Then
        assertEquals(2, result.getBody().size());
        assertEquals(product1.getId(), result.getBody().get(0).getId());
        assertEquals(product1.getName(), result.getBody().get(0).getName());
        assertEquals(product1.getDescription(), result.getBody().get(0).getDescription());

        assertEquals(product2.getId(), result.getBody().get(1).getId());
        assertEquals(product2.getName(), result.getBody().get(1).getName());
        assertEquals(product2.getDescription(), result.getBody().get(1).getDescription());
        verify(productRepository, times(1)).findAll();
    }
}
