package com.example.nobsv2.product.headers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.nobsv2.product.model.Product;

@RestController
public class HeaderController {
    @GetMapping("/header")
    public String getRegionalResponse(@RequestHeader(required = false, defaultValue = "US") String region) {
        // Normally abstract this into service class
        if(region.equals("US")) return "BALD EAGLE FREEDOM";
        
        if(region.equals("CAN")) return "MAPLE SYRUP";

        return "Country Not Supported";
    }

    // ACCEPT
    @GetMapping(value = "/header/product", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Product> getProduct() {
        Product product = new Product();
        product.setId(1);
        product.setName("Some crazy product");
        product.setDescription("I dont know man just cop this product trust me");
        product.setPrice(9.99);

        return ResponseEntity.ok(product);
    }

}
