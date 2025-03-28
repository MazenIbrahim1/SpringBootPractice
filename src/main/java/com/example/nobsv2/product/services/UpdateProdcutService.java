package com.example.nobsv2.product.services;

import java.util.Optional;

// import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.nobsv2.Command;
import com.example.nobsv2.exceptions.ProductNotFoundException;
import com.example.nobsv2.product.ProductRepository;
import com.example.nobsv2.product.model.ProductDTO;
import com.example.nobsv2.product.model.Product;
import com.example.nobsv2.product.model.UpdateProductCommand;
import com.example.nobsv2.product.validators.ProductValidator;

@Service
public class UpdateProdcutService implements Command<UpdateProductCommand, ProductDTO> {

    private final ProductRepository productRepository;

    public UpdateProdcutService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    // Avoiding stale data
    //@CacheEvict(value = "productCache", key = "#input.getId()") 
    @CachePut(value = "productCache", key = "#input.getId()") 
    // Evict -> throws cached value away
    // Put -> throws cached value away and replace it with what is returned
    public ResponseEntity<ProductDTO> execute(UpdateProductCommand input) {
        Optional<Product> productOptional = productRepository.findById(input.getId());
        if(productOptional.isPresent()) {
            // Update values
            Product product = input.getProduct();
            product.setId(input.getId());
            ProductValidator.execute(product);
            // Save can update or create new
            productRepository.save(product);
            return ResponseEntity.ok().body(new ProductDTO(product));
        }

        throw new ProductNotFoundException();
    }
}
