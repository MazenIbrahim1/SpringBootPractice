package com.example.nobsv2;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.example.nobsv2.exceptions.ProductNotValidException;
import com.example.nobsv2.product.model.Product;
import com.example.nobsv2.product.validators.ProductValidator;

public class ValidatorsTest {

    @Test
    public void testValidProduct() {
        Product product = new Product();
        product.setName("Valid Product");
        product.setDescription("This description is definitely more than twenty characters.");
        product.setPrice(10.0);
        assertDoesNotThrow(() -> ProductValidator.execute(product));
    }

    @Test
    public void testEmptyName() {
        Product product = new Product();
        product.setName("   ");
        product.setDescription("This description is definitely more than twenty characters.");
        product.setPrice(10.0);
        assertThrows(ProductNotValidException.class, () -> ProductValidator.execute(product));
    }

    @Test
    public void testShortDescription() {
        Product product = new Product();
        product.setName("Valid Name");
        product.setDescription("Too short");
        product.setPrice(10.0);
        assertThrows(ProductNotValidException.class, () -> ProductValidator.execute(product));
    }

    @Test
    public void testNegativePrice() {
        Product product = new Product();
        product.setName("Valid Name");
        product.setDescription("This description is definitely more than twenty characters.");
        product.setPrice(-1.0);
        assertThrows(ProductNotValidException.class, () -> ProductValidator.execute(product));
    }
}
