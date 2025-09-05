package br.com.joaobarbosa.springjwt.modules.products.dto;

import br.com.joaobarbosa.springjwt.modules.products.Product;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductResponse(UUID id, String name, BigDecimal price) {
    public ProductResponse(Product product) {
        this(product.getId(), product.getName(), product.getPrice());
    }
}