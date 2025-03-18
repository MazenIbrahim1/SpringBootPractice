package com.example.nobsv2.product.services;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.nobsv2.Query;
import com.example.nobsv2.product.ProductRepository;
import com.example.nobsv2.product.model.ProductDTO;

@Service
public class SearchProductsService implements Query<String, List<ProductDTO>> {
    private final ProductRepository productRepository;

    public SearchProductsService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<List<ProductDTO>> execute(String name) {
        return ResponseEntity.ok().body(productRepository.findByNameOrDescriptionContaining(name)
            .stream()
            .map(ProductDTO::new)
            .toList());
    }

}
