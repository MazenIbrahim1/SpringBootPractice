package com.example.nobsv2;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

import com.example.nobsv2.product.ProductRepository;
import com.example.nobsv2.product.model.Product;
import com.example.nobsv2.product.model.ProductDTO;
import com.example.nobsv2.product.services.GetProductByIdService;

public class GetProductServiceTest {

    @Mock // what to mock the response of -> need this dependency to run the test
    private ProductRepository productRepository;

    @InjectMocks // the thing we are testing
    private GetProductByIdService getProductByIdService;

    @BeforeEach // things to run before each test -> setting up to test
    public void setup() {
        // Initializes the repository and service class
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void given_product_exists_when_product_get_service_return_product_dto() {
        // Given
        Product product = new Product();
        product.setId(1);
        product.setName("Product Name");
        product.setDescription("Some description that is more than 20 chars");
        product.setPrice(9.99);

        when(productRepository.findById(1)).thenReturn(Optional.of(product));

        // When
        ResponseEntity<ProductDTO> response = getProductByIdService.execute(1);

        // Then -> assertion(expected, actual)
        assertEquals(ResponseEntity.ok(new ProductDTO(product)), response);

        // Verify product repository only called once
        verify(productRepository, times(1)).findById(1);
    }
}
