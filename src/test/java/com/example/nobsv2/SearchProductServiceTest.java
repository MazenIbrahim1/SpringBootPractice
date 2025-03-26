package com.example.nobsv2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.nobsv2.product.ProductRepository;
import com.example.nobsv2.product.model.Product;
import com.example.nobsv2.product.model.ProductDTO;
import com.example.nobsv2.product.services.SearchProductsService;

public class SearchProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private SearchProductsService searchProductsService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @SuppressWarnings("null")
    @Test
    public void testSearchProductsService() {
        // Given
        String searchTerm = "Test";
        Product product = new Product();
        product.setId(1);
        product.setName("Test Product");
        product.setDescription("This description is more than twenty characters.");
        product.setPrice(19.99);
        List<Product> mockedProducts = Arrays.asList(product);
        when(productRepository.findByNameOrDescriptionContaining(searchTerm)).thenReturn(mockedProducts);
        
        // When
        var response = searchProductsService.execute(searchTerm);
        List<ProductDTO> dtos = response.getBody();
        
        // Then
        assertEquals(1, dtos.size());
        assertEquals(product.getId(), dtos.get(0).getId());
        assertEquals(product.getName(), dtos.get(0).getName());
        assertEquals(product.getDescription(), dtos.get(0).getDescription());
    }
}
