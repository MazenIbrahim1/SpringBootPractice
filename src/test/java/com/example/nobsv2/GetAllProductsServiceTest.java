package com.example.nobsv2;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.nobsv2.product.ProductRepository;
import com.example.nobsv2.product.services.CreateProductService;

public class GetAllProductsServiceTest {
    // TO IMPLEMENT

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private CreateProductService createProductService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

}
