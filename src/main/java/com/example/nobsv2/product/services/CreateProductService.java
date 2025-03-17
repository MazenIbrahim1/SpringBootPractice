package com.example.nobsv2.product.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.nobsv2.Command;
import com.example.nobsv2.exceptions.ProductNotValidException;
import com.example.nobsv2.product.ProductRepository;
import com.example.nobsv2.product.model.Product;
import com.example.nobsv2.product.model.ProductDTO;
import com.mysql.cj.util.StringUtils;

@Service
public class CreateProductService implements Command<Product, ProductDTO> {

    private final ProductRepository productRepository;

    public CreateProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<ProductDTO> execute(Product product) {
        // Validate product before saving
        if(StringUtils.isEmptyOrWhitespaceOnly(product.getName())) {
            throw new ProductNotValidException("Name is required");
        }

        if(product.getDescription().length() < 20) {
            throw new ProductNotValidException("Description must be more than 20 characters");
        }

        if(product.getPrice() == null || product.getPrice() < 0.00) {
            throw new ProductNotValidException("Price cannot be negative");
        }

        // Save returns product entity
        Product savedProduct = productRepository.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ProductDTO(savedProduct));
    }
}
