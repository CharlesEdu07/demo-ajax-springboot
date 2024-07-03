package br.com.charlesedu.demoajax.dto;

import java.math.BigDecimal;

import br.com.charlesedu.demoajax.domain.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SaleDTO(@NotNull Long id, String title, @NotNull(message = "Um título é requerido") String description,
        @NotBlank(message = "Uma imagem é requerida") String imageLink,
        @NotNull(message = "O preço é requirido") BigDecimal price,
        @NotNull(message = "Uma categoria é requerida") Category category) {
}
