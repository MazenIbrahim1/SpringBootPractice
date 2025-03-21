package com.example.nobsv2.product;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.nobsv2.product.model.Product;
import com.example.nobsv2.product.model.ProductDTO;
import com.example.nobsv2.product.model.UpdateProductCommand;
import com.example.nobsv2.product.services.CreateProductService;
import com.example.nobsv2.product.services.DeleteProductService;
import com.example.nobsv2.product.services.GetProductByIdService;
import com.example.nobsv2.product.services.GetProductsService;
import com.example.nobsv2.product.services.SearchProductsService;
import com.example.nobsv2.product.services.UpdateProdcutService;

@RestController
public class ProductController {
    // Constructor Dependency Injection
    private final CreateProductService createProductService;
    private final GetProductsService getProductService;
    private final UpdateProdcutService updateProdcutService;
    private final DeleteProductService deleteProductService;
    private final GetProductByIdService getProductByIdService;
    private final SearchProductsService searchProductsService;

    public ProductController(CreateProductService createProductService, 
                            DeleteProductService deleteProductService, 
                            UpdateProdcutService updateProdcutService, 
                            GetProductsService getProductService,
                            GetProductByIdService getProductByIdService,
                            SearchProductsService searchProductsService) {
                                
        this.createProductService = createProductService;
        this.getProductService = getProductService;
        this.updateProdcutService = updateProdcutService;
        this.deleteProductService = deleteProductService;
        this.getProductByIdService = getProductByIdService;
        this.searchProductsService = searchProductsService;
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

    @PostMapping("/product")
    public ResponseEntity<ProductDTO> createProduct(@RequestBody Product product) {
        return createProductService.execute(product);
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> getProducts() {
        return getProductService.execute(null);
    }

    @GetMapping("/product/search")
    public ResponseEntity<List<ProductDTO>> searchProductsByName(@RequestParam String name) {
        return searchProductsService.execute(name);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Integer id) {
        return getProductByIdService.execute(id);
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Integer id, @RequestBody Product product) {
        return updateProdcutService.execute(new UpdateProductCommand(id, product));
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id) {
        return deleteProductService.execute(id);
    }
}
