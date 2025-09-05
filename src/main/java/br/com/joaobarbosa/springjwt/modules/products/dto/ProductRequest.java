package br.com.joaobarbosa.springjwt.modules.products.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;


public record ProductRequest (
    @NotBlank
    @NotNull
    String name,

    @NotNull
    BigDecimal price
) {}
