package br.com.charlesedu.demoajax.dto;

import java.math.BigDecimal;

import org.springframework.format.annotation.NumberFormat;

import br.com.charlesedu.demoajax.domain.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class SaleDTO {
        @NotNull
        Long id;

        @NotBlank(message = "Um título é requerido")
        String title;

        @NotNull(message = "Um título é requerido")
        String description;

        @NotBlank(message = "Uma imagem é requerida")
        String imageLink;

        @NotNull(message = "O preço é requirido")
        @NumberFormat(style = NumberFormat.Style.CURRENCY, pattern = "#,##0.00")
        BigDecimal price;

        @NotNull(message = "Uma categoria é requerida")
        Category category;

        public SaleDTO() {
        }

        public SaleDTO(@NotNull Long id, @NotBlank(message = "Um título é requerido") String title,
                        @NotNull(message = "Um título é requerido") String description,
                        @NotBlank(message = "Uma imagem é requerida") String imageLink,
                        @NotNull(message = "O preço é requirido") BigDecimal price,
                        @NotNull(message = "Uma categoria é requerida") Category category) {
                this.id = id;
                this.title = title;
                this.description = description;
                this.imageLink = imageLink;
                this.price = price;
                this.category = category;
        }

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getTitle() {
                return title;
        }

        public void setTitle(String title) {
                this.title = title;
        }

        public String getDescription() {
                return description;
        }

        public void setDescription(String description) {
                this.description = description;
        }

        public String getImageLink() {
                return imageLink;
        }

        public void setImageLink(String imageLink) {
                this.imageLink = imageLink;
        }

        public BigDecimal getPrice() {
                return price;
        }

        public void setPrice(BigDecimal price) {
                this.price = price;
        }

        public Category getCategory() {
                return category;
        }

        public void setCategory(Category category) {
                this.category = category;
        }

        @Override
        public String toString() {
                return "SaleDTO [id=" + id + ", title=" + title + ", description=" + description + ", imageLink="
                                + imageLink + ", price=" + price + ", category=" + category + "]";
        }
}
