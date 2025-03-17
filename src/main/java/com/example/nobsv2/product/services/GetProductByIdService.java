package com.example.nobsv2.product.services;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.nobsv2.Query;
import com.example.nobsv2.product.ProductRepository;
import com.example.nobsv2.product.model.Product;
import com.example.nobsv2.product.model.ProductDTO;

@Service
public class GetProductByIdService implements Query<Integer, ProductDTO> {
    private final ProductRepository productRepository;

    public GetProductByIdService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<ProductDTO> execute(Integer input) {
        // Optional in case there isn't a product with such ID
        Optional<Product> productOptional = productRepository.findById(input);
        
        if(productOptional.isPresent()) {
            return ResponseEntity.ok().body(new ProductDTO(productOptional.get()));
        }

        // Product Not found Exception

        return null;
    }
}
