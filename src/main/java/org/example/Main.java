package org.example;

import org.example.product.Product;

public class Main {
    static void main() {
        Product product1 = new Product.Builder()
                .id("1")
                .name("Pensel")
                .category(Product.Category.TOOLS)
                .rating(3.5)
                .build();

        IO.println("Product: " +
                product1.getId() + ", " +
                product1.getName() + ", " +
                product1.getCategory() + ", " +
                product1.getRating() + ", " +
                product1.getCreatedDate() + ", " +
                product1.getModifiedDate());
    }
}
