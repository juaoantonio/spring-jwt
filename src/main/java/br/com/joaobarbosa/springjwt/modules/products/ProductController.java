package br.com.joaobarbosa.springjwt.modules.products;

import br.com.joaobarbosa.springjwt.modules.products.dto.ProductRequest;
import br.com.joaobarbosa.springjwt.modules.products.dto.ProductResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductRepository productRepository;

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductResponse> response = products.stream()
                .map(ProductResponse::new)
                .toList();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<UUID> createProduct(@RequestBody @Valid ProductRequest productRequest) {
        Product product = new Product(productRequest);
        var result = this.productRepository.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(result.getId());
    }
}
