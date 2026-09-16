package org.example;

import org.example.discount.DiscountDecorator;
import org.example.product.Product;
import org.example.product.Product.Category;
import org.example.product.Sellable;

import java.math.BigDecimal;

public class Main {
    static void main() {
        Product product1 = new Product.Builder()
                .id("1")
                .name("Pensel")
                .category(Category.TOOLS)
                .rating(3.5)
                .price(BigDecimal.valueOf(1000))
                .build();

        Sellable discountedProduct = new DiscountDecorator(product1, 0.25);

        IO.println("Product: " +
                product1.getId() + ", " +
                product1.getName() + ", " +
                product1.getCategory() + ", " +
                product1.getRating() + ", " +
                product1.getPrice() + ", " +
                product1.getCreatedDate() + ", " +
                product1.getModifiedDate());

        IO.println("Discounted price: " + discountedProduct.getPrice());
    }
}
