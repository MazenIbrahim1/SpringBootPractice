package com.example.nobsv2.product;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    // Constructor Dependency Injection
    private final CreateProductService createProductService;
    private final GetProductService getProductService;
    private final UpdateProdcutService updateProdcutService;
    private final DeleteProductService deleteProductService;

    public ProductController(CreateProductService createProductService, 
                            DeleteProductService deleteProductService, 
                            UpdateProdcutService updateProdcutService, 
                            GetProductService getProductService) {
        this.createProductService = createProductService;
        this.getProductService = getProductService;
        this.updateProdcutService = updateProdcutService;
        this.deleteProductService = deleteProductService;
    }

    // Dependency Injection
    // Field Injection (Using Autowired = Not good) -> Constructor injection is better
    // @Autowired
    // private CreateProductService createProductService;
    // @Autowired
    // private DeleteProductService deleteProductService;
    // @Autowired
    // private UpdateProdcutService updateProdcutService;
    // @Autowired
    // private GetProductService getProductService;

    @PostMapping
    public ResponseEntity<String> createProduct() {
        return createProductService.execute(null);
    }

    @GetMapping
    public ResponseEntity<String> getProduct() {
        return getProductService.execute(null);
    }

    @PutMapping
    public ResponseEntity<String> updateProduct() {
        return updateProdcutService.execute(null);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteProduct() {
        return deleteProductService.execute(null);
    }
}
