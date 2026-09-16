package org.example.product;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Product implements Sellable {
    public enum Category {
        TOOLS,
        PAINT,
        WOODWORKING,
        MISC,
    }

    private final String id;
    private final String name;
    private final Category category;
    private final double rating;
    private final BigDecimal price;
    private final LocalDateTime createdDate;
    private final LocalDateTime modifiedDate;

    private Product(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.category = builder.category;
        this.rating = builder.rating;
        this.price = builder.price;
        this.createdDate = builder.createdDate;
        this.modifiedDate = builder.modifiedDate;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public double getRating() {
        return rating;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    public static class Builder {
        private String id;
        private String name;
        private Category category;
        private Double rating;
        private BigDecimal price;
        private LocalDateTime createdDate;
        private LocalDateTime modifiedDate;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder category(Category category) {
            this.category = category;
            return this;
        }

        public Builder rating(double rating) {
            this.rating = rating;
            return this;
        }

        public Builder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public Builder createdAt(LocalDateTime createdAt) {
            this.createdDate = createdAt;
            return this;
        }

        public Builder updatedAt(LocalDateTime updatedAt) {
            this.modifiedDate = updatedAt;
            return this;
        }

        public Product build() {
            if(id == null || id.isBlank()) {
                throw new IllegalArgumentException("Produkten saknar ett ID");
            }

            if(name == null || name.isBlank()) {
                throw new IllegalArgumentException("Produkten saknar ett namn");
            }

            if(category == null) {
                category = Category.MISC;
            }

            if(rating == null) {
                rating = 0.0;
            }

            if(price == null) {
                throw new IllegalArgumentException("Produkten saknar ett pris");
            }

            if(createdDate == null) {
                createdDate = LocalDateTime.now();
            }

            if(modifiedDate == null) {
                modifiedDate = LocalDateTime.now();
            }

            return new Product(this);
        }
    }
}
