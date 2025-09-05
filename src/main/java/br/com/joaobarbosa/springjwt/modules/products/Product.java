package br.com.joaobarbosa.springjwt.modules.products;

import br.com.joaobarbosa.springjwt.modules.products.dto.ProductRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Entity(name = "products")
@Table(name = "products")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    public Product(ProductRequest productRequest) {
        this.name = productRequest.name();
        this.price = productRequest.price();
    }
}
