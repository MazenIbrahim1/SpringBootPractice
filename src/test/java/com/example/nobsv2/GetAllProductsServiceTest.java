package com.example.nobsv2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.nobsv2.product.ProductRepository;
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
        
    }
}
