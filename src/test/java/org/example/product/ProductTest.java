package org.example.product;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

class ProductTest {
    @Test
    void createProductWithNoId_ThrowsIllegalArgumentException() {
        Product.Builder productBuilder = new Product.Builder()
                .name("Test");

        assertThatThrownBy(productBuilder::build)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Produkten saknar ett ID");
    }

    @Test
    void createProductWithNoName_ThrowsIllegalArgumentException() {
        Product.Builder productBuilder = new Product.Builder()
                .id("1")
                .name("  ");

        assertThatThrownBy(productBuilder::build)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Produkten saknar ett namn");
    }

    @Test
    void createProductWithMissingOptionalFields_UsesFallbackValues() {
        LocalDateTime before = LocalDateTime.now().minusSeconds(1);

        Product product = new Product.Builder()
                .id("1")
                .name("Test")
                .build();

        LocalDateTime after = LocalDateTime.now().plusSeconds(1);

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(product.getCategory()).isEqualTo(Product.Category.MISC);
            softly.assertThat(product.getRating()).isEqualTo(0.0);
            softly.assertThat(product.getCreatedDate())
                    .isBetween(before, after);
            softly.assertThat(product.getModifiedDate())
                    .isBetween(before, after);
        });
    }
}
