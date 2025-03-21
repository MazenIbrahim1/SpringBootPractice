package com.example.nobsv2.product.services;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.nobsv2.Query;
import com.example.nobsv2.exceptions.ProductNotFoundException;
import com.example.nobsv2.product.ProductRepository;
import com.example.nobsv2.product.model.Product;
import com.example.nobsv2.product.model.ProductDTO;

@Service
public class GetProductByIdService implements Query<Integer, ProductDTO> {
    private final ProductRepository productRepository;

    private static final Logger logger = LoggerFactory.getLogger(GetProductByIdService.class);

    public GetProductByIdService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @Cacheable("productCache")
    public ResponseEntity<ProductDTO> execute(Integer input) {
        logger.info("Executing " + getClass() + " input: " + input);
        // Optional in case there isn't a product with such ID
        Optional<Product> productOptional = productRepository.findById(input);
        if(productOptional.isPresent()) {
            return ResponseEntity.ok().body(new ProductDTO(productOptional.get()));
        }

        throw new ProductNotFoundException();
    }
}
